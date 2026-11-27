package com.example.spring_xp_monolith.Controllers.Errors;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.example.spring_xp_monolith.dto.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // ---------- 400 ----------

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(fe -> fe.isBindingFailure()
                        ? "Invalid value '" + fe.getRejectedValue() + "' for " + fe.getField()
                        : fe.getDefaultMessage())
                .or(() -> ex.getBindingResult().getGlobalErrors().stream()
                        .findFirst()
                        .map(ObjectError::getDefaultMessage))
                .orElse("Validation failed");
        return build(HttpStatus.BAD_REQUEST, msg);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorResponse> handleMethodValidation(HandlerMethodValidationException ex) {
        String msg = ex.getAllErrors().stream()
                .findFirst()
                .map(MessageSourceResolvable::getDefaultMessage)
                .orElse("Validation failed");
        return build(HttpStatus.BAD_REQUEST, msg);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        String msg = ex.getConstraintViolations().stream()
                .findFirst()
                .map(ConstraintViolation::getMessage)
                .orElse("Validation failed");
        return build(HttpStatus.BAD_REQUEST, msg);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleUnreadable(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getMostSpecificCause();

        if (cause instanceof InvalidFormatException ife) {
            String field = fieldPath(ife);
            Class<?> target = ife.getTargetType();
            if (target != null && target.isEnum()) {
                String allowed = Arrays.stream(target.getEnumConstants())
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));
                return build(HttpStatus.BAD_REQUEST,
                        "Invalid value '" + ife.getValue() + "' for " + field + ". Allowed: " + allowed);
            }
            return build(HttpStatus.BAD_REQUEST,
                    "Invalid value '" + ife.getValue() + "' for " + field
                            + (target != null ? ". Expected type: " + target.getSimpleName() : ""));
        }

        if (cause instanceof MismatchedInputException mie && !mie.getPath().isEmpty()) {
            return build(HttpStatus.BAD_REQUEST, "Invalid value for " + fieldPath(mie));
        }

        if (cause instanceof JsonProcessingException) {
            return build(HttpStatus.BAD_REQUEST, "Malformed JSON in request body");
        }

        return build(HttpStatus.BAD_REQUEST, "Malformed JSON or invalid value in request body");
    }

    private static String fieldPath(JsonMappingException ex) {
        StringBuilder sb = new StringBuilder();
        for (JsonMappingException.Reference ref : ex.getPath()) {
            if (ref.getFieldName() != null) {
                if (sb.length() > 0) sb.append('.');
                sb.append(ref.getFieldName());
            } else if (ref.getIndex() >= 0) {
                sb.append('[').append(ref.getIndex()).append(']');
            }
        }
        return sb.length() > 0 ? sb.toString() : "request body";
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return build(HttpStatus.BAD_REQUEST, "Invalid value for parameter '" + ex.getName() + "'");
    }

    @ExceptionHandler({MissingServletRequestParameterException.class, MissingServletRequestPartException.class})
    public ResponseEntity<ErrorResponse> handleMissingParam(Exception ex) {
        return build(HttpStatus.BAD_REQUEST, orDefault(ex.getMessage(), "Missing request parameter"));
    }

    @ExceptionHandler(InvalidPropertyException.class)
    public ResponseEntity<ErrorResponse> handleInvalidProperty(InvalidPropertyException ex) {
        return build(HttpStatus.BAD_REQUEST, "Invalid field '" + ex.getPropertyName() + "'");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        return build(HttpStatus.BAD_REQUEST, orDefault(ex.getMessage(), "Bad request"));
    }

    // ---------- 401 / 403 ----------

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthentication(AuthenticationException ex) {
        return build(HttpStatus.UNAUTHORIZED, "Invalid email or password");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex) {
        return build(HttpStatus.FORBIDDEN, "Access denied");
    }

    // ---------- 404 / 405 / 413 ----------

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        return build(HttpStatus.NOT_FOUND, orDefault(ex.getMessage(), "Resource not found"));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResource(NoResourceFoundException ex) {
        return build(HttpStatus.NOT_FOUND, "Endpoint not found");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return build(HttpStatus.METHOD_NOT_ALLOWED, orDefault(ex.getMessage(), "Method not allowed"));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        return build(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                "Unsupported content type. Send the request as multipart/form-data");
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleMaxUpload(MaxUploadSizeExceededException ex) {
        return build(HttpStatus.PAYLOAD_TOO_LARGE, "Uploaded file is too large");
    }

    // ---------- 500 ----------

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        log.error("Unhandled exception", ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
    }

    // ---------- helpers ----------

    private static ResponseEntity<ErrorResponse> build(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new ErrorResponse(message));
    }

    private static String orDefault(String msg, String fallback) {
        return (msg == null || msg.isBlank()) ? fallback : msg;
    }
}

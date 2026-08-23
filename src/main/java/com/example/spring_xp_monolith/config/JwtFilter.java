package com.example.spring_xp_monolith.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.spring_xp_monolith.Services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
    
    private final JwtService jwtService;

    private final ApplicationContext context;

    JwtFilter(JwtService jwtService,ApplicationContext context){
        this.jwtService = jwtService;
        this.context = context;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException,ServletException{
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        String role = null;

        if(authHeader != null && authHeader.startsWith("Bearer")){
            token = authHeader.substring(7);
            username = jwtService.extractUserName(token);
            role = jwtService.extractRole(token);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = context.getBean();
        }
    }

}

//
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserLookupService userLookupService;

    public JwtFilter(
            JwtService jwtService,
            UserLookupService userLookupService) {

        this.jwtService = jwtService;
        this.userLookupService = userLookupService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws IOException, ServletException {

        String authHeader = request.getHeader("Authorization");

        String token = null;
        String username = null;
        String role = null;

        if (authHeader != null &&
            authHeader.startsWith("Bearer ")) {

            token = authHeader.substring(7);

            username = jwtService.extractUserName(token);
            role = jwtService.extractRole(token);
        }

        if (username != null &&
            SecurityContextHolder.getContext()
                    .getAuthentication() == null) {

            UserDetails userDetails =
                    userLookupService.loadUser(username, role);

            if (jwtService.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}

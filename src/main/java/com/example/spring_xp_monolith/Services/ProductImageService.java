package com.example.spring_xp_monolith.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.tasks.UnsupportedFormatException;

@Service 
public class ProductImageService implements  WebMvcConfigurer{

    @Value ("${app.upload-dir:uploads}")
    private String uploadDir;

    public String uploadProductImage(MultipartFile image,String imageType,int width, int height){
        return uploadImage(image, "products", imageType, width, height);
    }

    // Saves to {uploadDir}/{folder}/{imageType}/ and returns the public path.
    public String uploadImage(MultipartFile image,String folder,String imageType,int width, int height){
        if(image==null || image.isEmpty()){
            throw new IllegalArgumentException(capitalize(imageType) + " image is required");
        }
        try {
            //fetch the directory
            Path directory = Paths.get(uploadDir,folder,imageType);

            //create a directory
            Files.createDirectories(directory);

            //Generate unique filename
            String filename = UUID.randomUUID()+".jpg";

            Path destination = directory.resolve(filename);

            //Resize and crop to exactly 400 x 400;
            Thumbnails.of(image.getInputStream()).size(width,height).crop(Positions.CENTER).outputFormat("jpg").toFile(destination.toFile());

            return "/uploads/"+folder+"/"+imageType+"/"+filename;

        } catch (UnsupportedFormatException e) {
            throw new IllegalArgumentException("Invalid image file for " + imageType + " image");
        } catch (IOException e) {
            throw new RuntimeException("Unable to upload " + imageType + " image", e);
        }
    }

    private static String capitalize(String s) {
        return s.isEmpty() ? s : Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

}

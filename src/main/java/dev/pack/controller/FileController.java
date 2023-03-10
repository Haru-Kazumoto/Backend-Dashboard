package dev.pack.controller;

import dev.pack.dto.FileResponse;
import dev.pack.service.FileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/io")
public class FileController {

    private final FileServiceImpl service;

    @Value("${project.image}")
    private String path;

    @PostMapping("/upload-file")
    public ResponseEntity<FileResponse> fileUpload(
            @RequestParam("image")MultipartFile image)  {
        String fileName;
        try {
            fileName = this.service.uploadImage(path, image);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    new FileResponse(
                            null,
                            "Image failed to upload due to error on server."
                    ), HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>(new FileResponse(fileName, "Image has uploaded."), HttpStatus.OK);
    }
}

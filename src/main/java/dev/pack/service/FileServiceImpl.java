package dev.pack.service;

import dev.pack.service.interfaces.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        //File name
        String name = file.getOriginalFilename();

        //Full path
        String filePath = path + File.separator + name;

        //Creating folder if no created
        File f = new File(path);
        if(!f.exists()) f.mkdir();

        //File Copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return name;
    }
}

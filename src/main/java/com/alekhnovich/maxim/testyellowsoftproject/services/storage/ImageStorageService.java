package com.alekhnovich.maxim.testyellowsoftproject.services.storage;

import com.alekhnovich.maxim.testyellowsoftproject.config.FileStorageConfig;
import com.alekhnovich.maxim.testyellowsoftproject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageStorageService {

    private final Path storagePath;

    @Autowired
    public ImageStorageService(FileStorageConfig fileStorageConfig) {
        storagePath = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(storagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void storeFile(MultipartFile file, User user) throws IOException{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path storeLocation = storagePath.resolve(fileName);
        user.setPhotoPath(storeLocation.toString());
        Files.copy(file.getInputStream(),storeLocation, StandardCopyOption.REPLACE_EXISTING);

    }
}

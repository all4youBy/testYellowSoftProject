package com.alekhnovich.maxim.testyellowsoftproject.controllers;


import com.alekhnovich.maxim.testyellowsoftproject.models.User;
import com.alekhnovich.maxim.testyellowsoftproject.models.dto.JSONMessage;
import com.alekhnovich.maxim.testyellowsoftproject.services.UserService;
import com.alekhnovich.maxim.testyellowsoftproject.services.storage.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ImageStorageService imageStorageService;

    @Autowired
    public UserController(UserService userService, ImageStorageService imageStorageService) {
        this.userService = userService;
        this.imageStorageService = imageStorageService;
    }

    @GetMapping
    public ResponseEntity<Object> getUsers(){
        List<User> users = userService.getItems();

        if(users.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new JSONMessage("Can't find any users."));

         return ResponseEntity.ok(users);
    }

    @GetMapping("/{login}")
    public ResponseEntity<User> getUserByLogin(@PathVariable String login){
        return ResponseEntity.ok(userService.getUserByLogin(login));
    }

    @PostMapping("/photo")
    public ResponseEntity<?> uploadPhoto(@RequestParam("file") MultipartFile file,Principal principal){
        User user = userService.getUserByLogin(principal.getName());
        try {
            imageStorageService.storeFile(file,user);
            userService.updateItem(user);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JSONMessage("Incorrect request param."));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new JSONMessage("uploaded."));
    }

    @GetMapping("/photo")
    public ResponseEntity<?> getUserPhotoPath(Principal authenticatedUser){
        User user = userService.getUserByLogin(authenticatedUser.getName());

        return ResponseEntity.ok(user.getPhotoPath());
    }
}

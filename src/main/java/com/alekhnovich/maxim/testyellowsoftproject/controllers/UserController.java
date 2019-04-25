package com.alekhnovich.maxim.testyellowsoftproject.controllers;


import com.alekhnovich.maxim.testyellowsoftproject.models.User;
import com.alekhnovich.maxim.testyellowsoftproject.models.dto.JSONMessage;
import com.alekhnovich.maxim.testyellowsoftproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getUsers(){
        List<User> users = userService.getItems();
        return users.isEmpty()?
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(new JSONMessage("Can't find any users.")):
                ResponseEntity.ok(users);
    }
}

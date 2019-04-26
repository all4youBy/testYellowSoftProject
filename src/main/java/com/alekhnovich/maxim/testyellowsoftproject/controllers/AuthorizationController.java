package com.alekhnovich.maxim.testyellowsoftproject.controllers;

import com.alekhnovich.maxim.testyellowsoftproject.models.User;
import com.alekhnovich.maxim.testyellowsoftproject.models.dto.JSONMessage;
import com.alekhnovich.maxim.testyellowsoftproject.models.dto.TokenDTO;
import com.alekhnovich.maxim.testyellowsoftproject.models.dto.requests.AuthenticationRequest;
import com.alekhnovich.maxim.testyellowsoftproject.models.dto.requests.RegistrationRequest;
import com.alekhnovich.maxim.testyellowsoftproject.services.SecurityService;
import com.alekhnovich.maxim.testyellowsoftproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthorizationController {

    private final SecurityService securityService;
    private final UserService userService;

    @Autowired
    public AuthorizationController(SecurityService securityService, UserService userService) {
        this.securityService = securityService;
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@RequestBody AuthenticationRequest request){
        String token = securityService.authenticate(request.getLogin(),request.getPassword());

        if(token.isEmpty())
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JSONMessage("Check login or password."));

        return ResponseEntity.ok(new TokenDTO(securityService.authenticate(request.getLogin(),request.getPassword())));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody RegistrationRequest request){
        if(!request.getPassword().equals(request.getConfirmPassword())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JSONMessage("Passwords doesn't match."));
        }
        User user = new User(request.getLogin(),request.getPassword());
        userService.addItem(user);
        return ResponseEntity.ok(user);
    }
}

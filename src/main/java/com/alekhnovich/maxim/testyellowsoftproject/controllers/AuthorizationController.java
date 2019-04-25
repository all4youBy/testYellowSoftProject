package com.alekhnovich.maxim.testyellowsoftproject.controllers;

import com.alekhnovich.maxim.testyellowsoftproject.models.dto.requests.AuthenticationRequest;
import com.alekhnovich.maxim.testyellowsoftproject.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationController {

    private final SecurityService securityService;

    @Autowired
    public AuthorizationController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(securityService.authenticate(request.getLogin(),request.getPassword()));
    }


}

package com.alekhnovich.maxim.testyellowsoftproject.controllers;

import com.alekhnovich.maxim.testyellowsoftproject.services.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class RunController {

    private final RunService runService;

    @Autowired
    public RunController(RunService runService) {
        this.runService = runService;
    }

    @GetMapping("/runs")
    public ResponseEntity<Object> getUserRuns(Principal principal){
        return ResponseEntity.ok(runService.getUserRuns(principal.getName()));
    }


}

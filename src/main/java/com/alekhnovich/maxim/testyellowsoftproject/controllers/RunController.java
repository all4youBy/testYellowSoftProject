package com.alekhnovich.maxim.testyellowsoftproject.controllers;

import com.alekhnovich.maxim.testyellowsoftproject.models.Run;
import com.alekhnovich.maxim.testyellowsoftproject.models.dto.RunDTO;
import com.alekhnovich.maxim.testyellowsoftproject.services.RunService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/runs")
public class RunController {

    private final RunService runService;
    private final ModelMapper mapper;

    @Autowired
    public RunController(RunService runService, ModelMapper mapper) {
        this.runService = runService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<RunDTO>> getUserRuns(Principal principal){
        return ResponseEntity.ok(
                runService.getUserRuns(principal.getName())
                 .stream()
                 .map(run -> mapper.map(run,RunDTO.class))
                 .collect(Collectors.toList()));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Run> addRun(@RequestBody RunDTO runAddRequest){

        Run run = runService.addItem(new Run(runAddRequest.getDistance(),
                                             runAddRequest.getRunTime(),
                                             runAddRequest.getRunDate()));
        return ResponseEntity.ok(run);
    }

    @DeleteMapping
    @PreAuthorize("#run.user.login == authentication.name")
    public void deleteRun(Run run){
        runService.deleteItem(run);
    }

    @PutMapping
    @PreAuthorize("#run.user.login == authentication.name")
    public ResponseEntity<Run> updateRun(Run run){
       return ResponseEntity.ok(runService.updateItem(run));
    }
}

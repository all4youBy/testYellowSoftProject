package com.alekhnovich.maxim.testyellowsoftproject.controllers;

import com.alekhnovich.maxim.testyellowsoftproject.models.dto.UserFullStatistic;
import com.alekhnovich.maxim.testyellowsoftproject.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic")
public class StatisticController {

    private final StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserFullStatistic> userStatistic(@PathVariable String username){
        return ResponseEntity.ok(statisticService.getUserStatistic(username));
    }
}

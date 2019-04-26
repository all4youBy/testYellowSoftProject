package com.alekhnovich.maxim.testyellowsoftproject.models.dto;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalTime;

@Data
public class UserWeekStatistic {
    @NonNull
    private Double avSpeed;
    @NonNull
    private LocalTime avTime;
    @NonNull
    private Double totalDistance;

}

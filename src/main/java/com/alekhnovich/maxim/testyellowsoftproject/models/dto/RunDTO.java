package com.alekhnovich.maxim.testyellowsoftproject.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class RunDTO {
    @NonNull
    private Double distance;
    @NonNull
    private LocalTime runTime;
    @NonNull
    private LocalDate runDate;
}

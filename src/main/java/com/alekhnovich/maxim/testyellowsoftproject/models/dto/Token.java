package com.alekhnovich.maxim.testyellowsoftproject.models.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class Token {
    @NonNull
    private String token;
}

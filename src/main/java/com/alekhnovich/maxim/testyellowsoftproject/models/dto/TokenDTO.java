package com.alekhnovich.maxim.testyellowsoftproject.models.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class TokenDTO {
    @NonNull
    private String token;
}

package com.alekhnovich.maxim.testyellowsoftproject.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserFullStatistic {
    @NonNull
    List<UserWeekStatistic> weekStatistics = new ArrayList<>();
}

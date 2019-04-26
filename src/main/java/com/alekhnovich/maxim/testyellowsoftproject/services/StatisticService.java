package com.alekhnovich.maxim.testyellowsoftproject.services;

import com.alekhnovich.maxim.testyellowsoftproject.models.dto.UserFullStatistic;
import com.alekhnovich.maxim.testyellowsoftproject.models.dto.UserWeekStatistic;

import java.util.List;

public interface StatisticService {
    UserFullStatistic getUserStatistic(String username);
}

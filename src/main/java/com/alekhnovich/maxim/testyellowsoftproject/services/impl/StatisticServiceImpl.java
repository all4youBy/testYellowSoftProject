package com.alekhnovich.maxim.testyellowsoftproject.services.impl;

import com.alekhnovich.maxim.testyellowsoftproject.models.Run;
import com.alekhnovich.maxim.testyellowsoftproject.models.dto.UserFullStatistic;
import com.alekhnovich.maxim.testyellowsoftproject.models.dto.UserWeekStatistic;
import com.alekhnovich.maxim.testyellowsoftproject.services.RunService;
import com.alekhnovich.maxim.testyellowsoftproject.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class StatisticServiceImpl implements StatisticService {

    private final RunService runService;

    @Autowired
    public StatisticServiceImpl(RunService runService) {
        this.runService = runService;
    }

    private UserWeekStatistic calculateStatisticItems(List<Run> runs) {
        double wholeDistance = 0;
        LocalTime wholeTime = LocalTime.ofNanoOfDay(0);
        long nanoSum = 0;

        for (Run run : runs) {
            wholeDistance += run.getDistance();
            wholeTime = wholeTime.plusNanos(run.getRunTime().toNanoOfDay());
            nanoSum += run.getRunTime().toNanoOfDay();
        }

        double averageSpeed = wholeDistance / (wholeTime.getHour() + (wholeTime.getMinute() / 60.0));

        return new UserWeekStatistic(averageSpeed, LocalTime.ofNanoOfDay(nanoSum / runs.size()), wholeDistance);
    }


    @Override
    public UserFullStatistic getUserStatistic(String username) {
        UserFullStatistic userFullStatistic = new UserFullStatistic();
        TemporalField weekOfYear = WeekFields.of(Locale.getDefault()).weekOfYear();
        Map<Integer, List<Run>> userRunWeeks = runService.getUserRuns(username)
                .stream()
                .collect(Collectors.groupingBy(run -> run.getRunDate().get(weekOfYear)
                ));

        for (Integer week : userRunWeeks.keySet()) {
            userFullStatistic.getWeekStatistics().add(calculateStatisticItems(userRunWeeks.get(week)));

        }
        return userFullStatistic;
    }
}

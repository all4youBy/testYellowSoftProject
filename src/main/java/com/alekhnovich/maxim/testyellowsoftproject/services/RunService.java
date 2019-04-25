package com.alekhnovich.maxim.testyellowsoftproject.services;

import com.alekhnovich.maxim.testyellowsoftproject.models.Run;

import java.util.List;

public interface RunService extends CrudService<Run,Integer> {
    List<Run> getUserRuns(String userName);
}

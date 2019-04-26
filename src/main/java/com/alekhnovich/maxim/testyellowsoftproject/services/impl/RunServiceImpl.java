package com.alekhnovich.maxim.testyellowsoftproject.services.impl;

import com.alekhnovich.maxim.testyellowsoftproject.models.Run;
import com.alekhnovich.maxim.testyellowsoftproject.repositories.RunRepository;
import com.alekhnovich.maxim.testyellowsoftproject.repositories.queries.RunSpecification;
import com.alekhnovich.maxim.testyellowsoftproject.services.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunServiceImpl extends CrudServiceImpl<Run,Integer> implements RunService {

    private final RunRepository runRepository;

    @Autowired
    public RunServiceImpl(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @Override
    public JpaRepository<Run, Integer> getRepository() {
        return runRepository;
    }

    @Override
    public List<Run> getUserRuns(String userLogin) {
        return runRepository.findAll(RunSpecification.userRuns(userLogin));
    }
}

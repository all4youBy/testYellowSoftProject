package com.alekhnovich.maxim.testyellowsoftproject.services;

import com.alekhnovich.maxim.testyellowsoftproject.models.Run;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface RunService extends CrudService<Run,Integer> {
    @PreAuthorize("#name == authentication.name")
    List<Run> getUserRuns(@Param("name") String userName);
}

package com.alekhnovich.maxim.testyellowsoftproject.repositories;

import com.alekhnovich.maxim.testyellowsoftproject.models.Run;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RunRepository extends JpaRepository<Run,Integer>, JpaSpecificationExecutor<Run> {
}

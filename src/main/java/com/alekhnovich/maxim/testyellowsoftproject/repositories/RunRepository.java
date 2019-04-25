package com.alekhnovich.maxim.testyellowsoftproject.repositories;

import com.alekhnovich.maxim.testyellowsoftproject.models.Run;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunRepository extends JpaRepository<Run,Integer> {
}

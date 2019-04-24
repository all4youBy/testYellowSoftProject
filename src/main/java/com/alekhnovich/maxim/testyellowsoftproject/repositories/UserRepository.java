package com.alekhnovich.maxim.testyellowsoftproject.repositories;

import com.alekhnovich.maxim.testyellowsoftproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
}

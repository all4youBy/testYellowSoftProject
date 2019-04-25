package com.alekhnovich.maxim.testyellowsoftproject.services.impl;

import com.alekhnovich.maxim.testyellowsoftproject.models.User;
import com.alekhnovich.maxim.testyellowsoftproject.repositories.UserRepository;
import com.alekhnovich.maxim.testyellowsoftproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CrudServiceImpl<User,Integer> implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public JpaRepository<User, Integer> getRepository() {
        return userRepository;
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }
}

package com.alekhnovich.maxim.testyellowsoftproject.services;

import com.alekhnovich.maxim.testyellowsoftproject.models.User;

public interface UserService extends CrudService<User,Integer> {
    User getUserByLogin(String login);
}

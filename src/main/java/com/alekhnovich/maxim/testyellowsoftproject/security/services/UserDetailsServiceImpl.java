package com.alekhnovich.maxim.testyellowsoftproject.security.services;

import com.alekhnovich.maxim.testyellowsoftproject.models.User;
import com.alekhnovich.maxim.testyellowsoftproject.security.roles.UserPrincipal;
import com.alekhnovich.maxim.testyellowsoftproject.security.roles.UserRole;
import com.alekhnovich.maxim.testyellowsoftproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Qualifier("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user =  userService.getUserByLogin(login);

        if(user == null)
            throw new UsernameNotFoundException(String.format("Can't find user with login %s",login));

        return new UserPrincipal(user, UserRole.USER);
    }
}

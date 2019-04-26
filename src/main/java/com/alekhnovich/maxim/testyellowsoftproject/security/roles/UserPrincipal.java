package com.alekhnovich.maxim.testyellowsoftproject.security.roles;


import com.alekhnovich.maxim.testyellowsoftproject.models.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails{

    private final static String ROLE_PREFIX = "ROLE_";

    @Getter
    @Setter
    private User user;

    @Setter
    @Getter
    private String token;

    @Setter
    @Getter
    private String userRole;

    public UserPrincipal(User user,UserRole userRole){
        this.user = user;
        this.userRole = ROLE_PREFIX + userRole.name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(userRole));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


package com.alekhnovich.maxim.testyellowsoftproject.services.impl;

import com.alekhnovich.maxim.testyellowsoftproject.security.roles.UserPrincipal;
import com.alekhnovich.maxim.testyellowsoftproject.security.services.SecurityUtils;
import com.alekhnovich.maxim.testyellowsoftproject.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final SecurityUtils securityUtils;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityServiceImpl(AuthenticationManager authenticationManager,
                                   @Qualifier("userDetailsServiceImpl")
                                           UserDetailsService userDetailsService,
                                   SecurityUtils securityUtils,
                                   PasswordEncoder passwordEncoder)
    {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.securityUtils = securityUtils;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public String authenticate(String login, String password) {
        try {
            UserDetails user = userDetailsService.loadUserByUsername(login);
            System.out.println(user.getUsername());
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login,password,user.getAuthorities());
            authenticationManager.authenticate(token);
            if(token.isAuthenticated())
                SecurityContextHolder.getContext().setAuthentication(token);

            return securityUtils.generateToken((UserPrincipal)user);

        }catch (BadCredentialsException e){
            throw new BadCredentialsException(e.getMessage(),e);
        }
    }
}

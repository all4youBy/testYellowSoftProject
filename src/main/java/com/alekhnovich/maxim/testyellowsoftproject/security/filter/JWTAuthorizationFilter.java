package com.alekhnovich.maxim.testyellowsoftproject.security.filter;

import com.alekhnovich.maxim.testyellowsoftproject.security.services.SecurityUtils;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private SecurityUtils securityUtils;

    private static final String HEADER = "Authorization";

    public JWTAuthorizationFilter(SecurityUtils securityUtils){
        this.securityUtils = securityUtils;
    }

    @Override
    protected void doFilterInternal(@Nullable HttpServletRequest request,
                                    @Nullable HttpServletResponse response,
                                    @Nullable FilterChain chain) throws IOException, ServletException {

        String header = null;

        if (request != null) {
            header = request.getHeader(HEADER);
        }

        String token;
        String email = null;
        String role = null;
        Date expDate = null;

        if(header != null && header.startsWith("Bearer ")){
            token = header.substring(7);
            email = securityUtils.getUserFromToken(token);
            role = securityUtils.getUserAuthority(token);
            expDate = securityUtils.getExpirationTimeFromToken(token);

        }

        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,null,  Collections.singletonList(new SimpleGrantedAuthority(role)));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        else if(email != null && SecurityContextHolder.getContext().getAuthentication() != null){
            Date curDate = new Date();
            if(curDate.after(expDate))
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }

        if (chain != null) {
            chain.doFilter(request,response);
        }

    }
}

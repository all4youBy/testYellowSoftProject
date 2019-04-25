package com.alekhnovich.maxim.testyellowsoftproject.security.services;

import com.alekhnovich.maxim.testyellowsoftproject.security.roles.UserPrincipal;
import com.alekhnovich.maxim.testyellowsoftproject.services.time.TimeService;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class SecurityUtils {

    public static final String BREARER_PREFIX = "Bearer ";

    @Value("${jwt.secret}")
    private String secret;

    @Getter
    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(UserPrincipal user) {

        final LocalDateTime createdDate = LocalDateTime.now();
        final LocalDateTime expirationDate = calculateExpirationDate(createdDate);
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(TimeService.parseLocalDateTimeToDate(createdDate))
                .setExpiration(TimeService.parseLocalDateTimeToDate(expirationDate))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUserFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationTimeFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public Date getTokenCreateTime(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
        Claims claims = getAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String getUserAuthority(String token) {
        return getAllClaims(token).get("role", String.class);
    }

    private LocalDateTime calculateExpirationDate(LocalDateTime createdDate) {
        return createdDate.plusMinutes(expiration);
    }
}
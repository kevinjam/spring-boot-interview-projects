package com.kevinjanvier.authJwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    public static final long ACCESS_TOKEN_EXPIRATION = 5 * 60 * 1000; // 5 minutes
    public static final long REFRESH_TOKEN_EXPIRATION = 3 * 24 * 60 * 60 * 1000; // 3 days
    private static final long LEEWAY = 5 * 60 * 1000; // 5 minutes
    @Value("${token.secret}")
    private  String secretKey;


    public String generateToken(String username, long expiration) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .setAllowedClockSkewSeconds(LEEWAY / 1000)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
package com.kevinjanvier.authJwt.service;

import com.kevinjanvier.authJwt.utils.JwtUtil;
import com.kevinjanvier.authJwt.request.AuthRequest;
import com.kevinjanvier.authJwt.response.AuthResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<AuthResponse> authenticateUser(AuthRequest authRequest) {
        return getAuthResponseResponseEntity(authRequest, jwtUtil);
    }

    public static ResponseEntity<AuthResponse> getAuthResponseResponseEntity(AuthRequest authRequest, JwtUtil jwtUtil) {
        if ("kevin".equals(authRequest.username()) && "hello@1234".equals(authRequest.password())) {
            var accessToken = jwtUtil.generateToken(authRequest.username(), JwtUtil.ACCESS_TOKEN_EXPIRATION);
            var refreshToken = jwtUtil.generateToken(authRequest.username(), JwtUtil.REFRESH_TOKEN_EXPIRATION);
            return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken, "Successful"));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new AuthResponse(null, null, "Invalid credentials"));
    }

    public ResponseEntity<AuthResponse> refreshAccessToken(String refreshToken) {
        try {
            var claims = jwtUtil.validateToken(refreshToken);
            var username = claims.getSubject();
            var accessToken = jwtUtil.generateToken(username, JwtUtil.ACCESS_TOKEN_EXPIRATION);
            return ResponseEntity.ok(new AuthResponse(accessToken, null, null));
        } catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(null, null, "Refresh token expired"));
        }
    }
}

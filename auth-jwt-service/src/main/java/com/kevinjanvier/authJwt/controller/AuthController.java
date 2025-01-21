package com.kevinjanvier.authJwt.controller;

import com.kevinjanvier.authJwt.request.AuthRequest;
import com.kevinjanvier.authJwt.response.AuthResponse;
import com.kevinjanvier.authJwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        return authService.authenticateUser(authRequest);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestParam String refreshToken) {
        return authService.refreshAccessToken(refreshToken);
    }
}

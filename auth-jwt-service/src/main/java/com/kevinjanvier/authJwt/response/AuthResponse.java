package com.kevinjanvier.authJwt.response;

public record AuthResponse(String refreshToken, String accessToken, String message) {
}

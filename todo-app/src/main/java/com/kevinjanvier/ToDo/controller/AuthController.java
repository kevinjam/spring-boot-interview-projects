package com.kevinjanvier.ToDo.controller;

import com.kevinjanvier.ToDo.entity.User;
import com.kevinjanvier.ToDo.request.AuthRequest;
import com.kevinjanvier.ToDo.response.UserResponse;
import com.kevinjanvier.ToDo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody AuthRequest authRequest) {
        var resp = userService.authentication(authRequest);
        log.info("Token: {}", resp.token());
        return ResponseEntity.ok().body(resp);
    }
}
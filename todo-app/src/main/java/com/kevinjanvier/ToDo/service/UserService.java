package com.kevinjanvier.ToDo.service;

import com.kevinjanvier.ToDo.entity.User;
import com.kevinjanvier.ToDo.exceptions.ResourceConflictException;
import com.kevinjanvier.ToDo.exceptions.ResourceNotFoundException;
import com.kevinjanvier.ToDo.repository.UserRepository;
import com.kevinjanvier.ToDo.request.AuthRequest;
import com.kevinjanvier.ToDo.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ResourceConflictException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public UserResponse authentication(AuthRequest userDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.username(), userDto.password()));
        String token = jwtService.generateToken(userDto.username());
        return new UserResponse(token);
    }
}

package com.example.CTO.service;

import com.example.CTO.dto.AuthRequest;
import com.example.CTO.dto.AuthResponse;
import com.example.CTO.dto.RegisterRequest;
import com.example.CTO.model.User;
import com.example.CTO.model.enums.UserRole;
import com.example.CTO.repository.UserRepository;
import com.example.CTO.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(UserRole.ROLE_USER);
        userRepository.save(user);
        return new AuthResponse(jwtService.generateToken(user.getUsername(), user.getRole().getName()));
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return new AuthResponse(jwtService.generateToken(user.getUsername(), user.getRole().getName()));
    }
}

package com.example.cto.service;

import com.example.cto.dto.AuthRequest;
import com.example.cto.dto.AuthResponse;
import com.example.cto.dto.RegisterRequest;
import com.example.cto.model.User;
import com.example.cto.model.enums.UserRole;
import com.example.cto.repository.UserRepository;
import com.example.cto.security.jwt.JwtService;
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

package com.example.cto.service.impl;

import com.example.cto.dto.RequestCreate;
import com.example.cto.dto.RequestResponse;
import com.example.cto.mapper.RequestMapper;
import com.example.cto.model.Request;
import com.example.cto.repository.RequestRepository;
import com.example.cto.repository.UserRepository;
import com.example.cto.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    @Override
    public void createRequest(RequestCreate requestCreate, Authentication authentication) {
        var username = authentication.getName();
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No user found"));
        var request = Request.build(requestCreate, user);
        requestRepository.save(request);


    }

    @Override
    public Page<RequestResponse> getAllRequests(Authentication authentication, Pageable pageable) {
        var username = authentication.getName();
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No user found"));
        var reqeuests = requestRepository.findAllAccepted(user.getId(), pageable);
        return reqeuests.map(requestMapper :: mapToDto);
    }
}

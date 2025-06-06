package com.example.cto.service.impl;

import com.example.cto.model.RequestHistory;
import com.example.cto.repository.RequestHistoryRepository;
import com.example.cto.repository.RequestRepository;
import com.example.cto.repository.UserRepository;
import com.example.cto.service.RequestHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestHistoryServiceImpl implements RequestHistoryService {

    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final RequestHistoryRepository requestHistoryRepository;

    @Override
    public void createRequestHistory(Authentication authentication, String status) {
        var username = authentication.getName();
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No user found"));
        var request = requestRepository.findById()
        var requestHistory = RequestHistory.build(user.getUsername())
    }
}

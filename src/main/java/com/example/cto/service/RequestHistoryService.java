package com.example.cto.service;

import org.springframework.security.core.Authentication;

public interface RequestHistoryService {
    void createRequestHistory(Authentication authentication, String status);
}

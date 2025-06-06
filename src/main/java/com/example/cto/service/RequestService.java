package com.example.cto.service;

import com.example.cto.dto.RequestCreate;
import com.example.cto.dto.RequestResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface RequestService {
    void createRequest(RequestCreate requestCreate, Authentication authentication);
    Page<RequestResponse> getAllRequests(Authentication authentication, Pageable pageable);
}

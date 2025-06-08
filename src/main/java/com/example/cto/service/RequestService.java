package com.example.cto.service;

import com.example.cto.dto.RequestCreateWithUser;
import com.example.cto.dto.RequestResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface RequestService {
    void createRequest(RequestCreateWithUser requestCreateWithUser);
    Page<RequestResponse> getAllNonRejectedRequests(Authentication authentication, Pageable pageable);
    Page<RequestResponse> getArchivedRequest(Authentication authentication, Pageable pageable);
    Page<RequestResponse> getAllRequestOfUser(Long id, Authentication authentication, Pageable pageable);
}

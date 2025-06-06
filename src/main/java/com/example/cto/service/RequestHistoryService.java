package com.example.cto.service;

import com.example.cto.dto.RequestHistoryDto;
import com.example.cto.dto.RequestHistoryResponse;
import com.example.cto.model.Request;
import com.example.cto.model.RequestHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface RequestHistoryService {
    RequestHistory createInitialRequestHistory(Request request);
    void updateHistoryForRequest(Long requestId, Authentication authentication);
    void finishWork(Long requestId, Authentication authentication);
    void rejectWork(Long requestId, Authentication authentication);
    Page<RequestHistoryResponse> getHistoryByUserId(Long userId, Authentication authentication, Pageable pageable);
}

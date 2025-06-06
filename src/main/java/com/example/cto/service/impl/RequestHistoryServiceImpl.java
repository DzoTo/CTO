package com.example.cto.service.impl;

import com.example.cto.model.Request;
import com.example.cto.model.RequestHistory;
import com.example.cto.model.enums.RequestStatus;
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

    private static final String INIT_MESSAGE = "Request has been accepted";
    @Override
    public RequestHistory createInitialRequestHistory(Request request) {
        return RequestHistory.build(request, INIT_MESSAGE);
    }
}

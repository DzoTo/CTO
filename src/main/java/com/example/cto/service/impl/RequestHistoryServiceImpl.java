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
    private static final String START_MESSAGE = "Responsible person started to work on your request";
    private static final String REJECT_MESSAGE = "Your request has been rejected";
    private static final String SUCCESS = "Your request has been done successfully";

    @Override
    public RequestHistory createInitialRequestHistory(Request request) {
        var initWorker = "Responsible person is not assigned yet";
        return RequestHistory.buildForUpdate(INIT_MESSAGE, RequestStatus.ACCEPTED, initWorker, request);
    }

    @Override
    public void updateHistoryForRequest(Long requestId, Authentication authentication) {
        var request = getRequest(requestId);
        var username = getUsername(authentication);
        var history = RequestHistory.buildForUpdate(START_MESSAGE,
                RequestStatus.IN_PROGRESS, username, request);
        request.getHistory().add(history);
        request.setStatus(RequestStatus.IN_PROGRESS);
        requestRepository.save(request);
    }

    @Override
    public void finishWork(Long requestId, Authentication authentication) {
        var request = getRequest(requestId);
        var username = getUsername(authentication);
        var history = RequestHistory.buildForUpdate(SUCCESS,
                RequestStatus.SUCCESS, username, request);
        request.getHistory().add(history);
        request.setStatus(RequestStatus.SUCCESS);
        requestRepository.save(request);
    }

    @Override
    public void rejectWork(Long requestId, Authentication authentication) {
        var request = getRequest(requestId);
        var username = getUsername(authentication);
        var history = RequestHistory.buildForUpdate(REJECT_MESSAGE,
                RequestStatus.REJECTED, username, request);
        request.getHistory().add(history);
        request.setStatus(RequestStatus.REJECTED);
        requestRepository.save(request);
    }

    private Request getRequest(Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("No such request"));
    }
    private String getUsername(Authentication authentication) {
        return authentication.getName();
    }
}

package com.example.cto.service.impl;

import com.example.cto.dto.RequestCreate;
import com.example.cto.dto.RequestResponse;
import com.example.cto.mapper.RequestMapper;
import com.example.cto.model.Request;
import com.example.cto.model.User;
import com.example.cto.repository.RequestRepository;
import com.example.cto.repository.UserRepository;
import com.example.cto.service.RequestHistoryService;
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
    private final RequestHistoryService requestHistoryService;

    @Override
    public void createRequest(RequestCreate requestCreate, Authentication authentication) {
        var user = getUser(authentication);
        var request = Request.build(requestCreate, user);
        var reqHist = requestHistoryService.createInitialRequestHistory(request);
        request.getHistory().add(reqHist);
        requestRepository.save(request);
    }

    @Override
    public Page<RequestResponse> getAllNonRejectedRequests(Authentication authentication, Pageable pageable) {
        var user = getUser(authentication);
        var reqeuests = requestRepository.findAllAccepted(user.getId(), pageable);
        return reqeuests.map(requestMapper :: mapToDto);
    }

    @Override
    public Page<RequestResponse> getArchivedRequest(Authentication authentication, Pageable pageable) {
        var user = getUser(authentication);
        var rejectedRequests = requestRepository.findAllRejected(user.getId(), pageable);
        return rejectedRequests.map(requestMapper :: mapToDto);
    }

    @Override
    public Page<RequestResponse> getAllRequestOfUser(Long id, Authentication authentication, Pageable pageable) {
        var user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No such user with id"));
        var requests = requestRepository.findAllByUserId(user.getId(), pageable);
        return requests.map(requestMapper :: mapToDto);
    }

    private User getUser(Authentication authentication) {
        var username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No user found"));
    }
}

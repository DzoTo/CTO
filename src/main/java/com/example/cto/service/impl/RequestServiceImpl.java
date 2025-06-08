package com.example.cto.service.impl;

import com.example.cto.dto.RequestCreateWithUser;
import com.example.cto.dto.RequestResponse;
import com.example.cto.mapper.Mapper;
import com.example.cto.model.Request;
import com.example.cto.model.User;
import com.example.cto.repository.RequestRepository;
import com.example.cto.repository.UserRepository;
import com.example.cto.service.MailService;
import com.example.cto.service.RequestHistoryService;
import com.example.cto.service.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final MailService mailService;
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final Mapper mapper;
    private final RequestHistoryService requestHistoryService;

    private static final String INIT_MESSAGE = "Request has been accepted";

    @Override
    public void createRequest(RequestCreateWithUser requestCreateWithUser) {
        var user = getUser(requestCreateWithUser.username());
        var request = Request.build(requestCreateWithUser.requestCreate(), user);
        var reqHist = requestHistoryService.createInitialRequestHistory(request);
        request.getHistory().add(reqHist);
        requestRepository.save(request);
        mailService.sendSimpleEmail(request.getContactEmail(), request.getTitle(), INIT_MESSAGE);
        log.info("Request has been created, notification has been sent to contact email");
    }

    @Override
    public Page<RequestResponse> getAllNonRejectedRequests(Authentication authentication, Pageable pageable) {
        var user = getUserAuth(authentication);
        var reqeuests = requestRepository.findAllAccepted(user.getId(), pageable);
        return reqeuests.map(mapper::mapRequestToDto);
    }

    @Override
    public Page<RequestResponse> getArchivedRequest(Authentication authentication, Pageable pageable) {
        var user = getUserAuth(authentication);
        var rejectedRequests = requestRepository.findAllRejected(user.getId(), pageable);
        return rejectedRequests.map(mapper::mapRequestToDto);
    }

    @Override
    public Page<RequestResponse> getAllRequestOfUser(Long id, Authentication authentication, Pageable pageable) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No such user with id"));
        var requests = requestRepository.findAllByUserId(user.getId(), pageable);
        return requests.map(mapper::mapRequestToDto);
    }

    private User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No user found"));
    }

    private User getUserAuth(Authentication authentication) {
        return getUser(authentication.getName());
    }
}

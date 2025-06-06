package com.example.cto.controller;

import com.example.cto.dto.RequestCreate;
import com.example.cto.dto.RequestResponse;
import com.example.cto.service.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/request")
@RequiredArgsConstructor
@Slf4j
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/new")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<String> createNewRequest(@RequestBody RequestCreate requestCreate, Authentication authentication) {
        try {
            requestService.createRequest(requestCreate, authentication);
            return ResponseEntity.ok("Successfully created new request");
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/my-requests")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Page<RequestResponse>> getMyRequests(Authentication authentication, Pageable pageable) {
        try {
            var requests = requestService.getAllNonRejectedRequests(authentication, pageable);
            return ResponseEntity.ok(requests);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Page<RequestResponse>> getRequests(@PathVariable Long id, Authentication authentication, Pageable pageable) {
        try {
            var requests = requestService.getAllRequestOfUser(id, authentication, pageable);
            return ResponseEntity.ok(requests);
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }
}

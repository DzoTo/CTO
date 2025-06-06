package com.example.cto.controller;

import com.example.cto.dto.RequestHistoryResponse;
import com.example.cto.dto.RequestResponse;
import com.example.cto.model.RequestHistory;
import com.example.cto.service.RequestHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
@Slf4j
public class RequestHistoryController {
    private final RequestHistoryService requestHistoryService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{id}/start")
    public ResponseEntity<String> startWork(@PathVariable Long id, Authentication authentication) {
        try {
            requestHistoryService.updateHistoryForRequest(id, authentication);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{id}/success")
    public ResponseEntity<String> finishWork(@PathVariable Long id, Authentication authentication) {
        try {
            requestHistoryService.finishWork(id, authentication);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{id}/reject")
    public ResponseEntity<String> rejectWork(@PathVariable Long id, Authentication authentication) {
        try {
            requestHistoryService.rejectWork(id, authentication);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Page<RequestHistoryResponse>> getRequests(
            @PathVariable("id") Long requestId,
            Authentication authentication, Pageable pageable) {
        try {
            var requests = requestHistoryService.getHistoryByUserId(requestId, authentication, pageable);
            return ResponseEntity.ok(requests);
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }
}

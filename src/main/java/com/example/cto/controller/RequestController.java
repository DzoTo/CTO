package com.example.cto.controller;

import com.example.cto.dto.RequestCreate;
import com.example.cto.dto.RequestResponse;
import com.example.cto.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/request")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/new")
    ResponseEntity<String> createNewRequest(@RequestBody RequestCreate requestCreate, Authentication authentication) {
        try {
            requestService.createRequest(requestCreate, authentication);
            return ResponseEntity.ok("Successfully created new request");
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


    @GetMapping("/my-requests")
    ResponseEntity<Page<RequestResponse>> getMyRequests(Authentication authentication, Pageable pageable) {
        try {
            var requests = requestService.getAllRequests(authentication, pageable);
            return ResponseEntity.ok(requests);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }
}

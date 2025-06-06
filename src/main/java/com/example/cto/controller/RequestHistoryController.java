package com.example.cto.controller;

import com.example.cto.model.RequestHistory;
import com.example.cto.service.RequestHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class RequestHistoryController {
    private final RequestHistoryService requestHistoryService;


    @PostMapping("/{id}/start")
    public ResponseEntity<String> startWork(@PathVariable Long id, Authentication authentication) {
        try {
            requestHistoryService.updateHistoryForRequest(id, authentication);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/success")
    public ResponseEntity<String> finishWork(@PathVariable Long id, Authentication authentication) {
        try {
            requestHistoryService.finishWork(id, authentication);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<String> rejectWork(@PathVariable Long id, Authentication authentication) {
        try {
            requestHistoryService.rejectWork(id, authentication);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

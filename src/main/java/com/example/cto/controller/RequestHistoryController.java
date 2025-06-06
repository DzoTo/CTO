package com.example.cto.controller;

import com.example.cto.model.RequestHistory;
import com.example.cto.service.RequestHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class RequestHistoryController {
    private final RequestHistoryService requestHistoryService;

//
//    @PostMapping("/new")
//    public ResponseEntity<String> createHistory(@RequestBody RequestHistory requestHistory) {
//
//    }
}

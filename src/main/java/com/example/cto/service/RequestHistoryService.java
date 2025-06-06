package com.example.cto.service;

import com.example.cto.model.Request;
import com.example.cto.model.RequestHistory;
import org.springframework.security.core.Authentication;

public interface RequestHistoryService {
    RequestHistory createInitialRequestHistory(Request request);
}

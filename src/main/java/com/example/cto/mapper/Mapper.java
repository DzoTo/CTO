package com.example.cto.mapper;

import com.example.cto.dto.RequestHistoryResponse;
import com.example.cto.dto.RequestResponse;
import com.example.cto.model.Request;
import com.example.cto.model.RequestHistory;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public RequestResponse mapRequestToDto(Request request) {
        return RequestResponse.builder()
                .title(request.getTitle())
                .status(request.getStatus())
                .createdAt(request.getCreatedAt())
                .description(request.getContent())
                .build();
    }

    public RequestHistoryResponse mapRequestHistoryToDto(RequestHistory requestHistory) {
        return RequestHistoryResponse.builder()
                .message(requestHistory.getMessage())
                .workerName(requestHistory.getWorkerName())
                .status(requestHistory.getStatus().getValue())
                .updatedAt(requestHistory.getUpdatedAt())
                .build();
    }
}


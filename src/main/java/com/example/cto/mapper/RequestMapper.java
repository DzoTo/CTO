package com.example.cto.mapper;

import com.example.cto.dto.RequestResponse;
import com.example.cto.model.Request;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper {
    public RequestResponse mapToDto(Request request) {
        return RequestResponse.builder()
                .title(request.getTitle())
                .status(request.getStatus())
                .createdAt(request.getCreatedAt())
                .description(request.getContent())
                .build();
    }
}


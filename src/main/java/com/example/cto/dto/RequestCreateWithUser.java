package com.example.cto.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
public record RequestCreateWithUser(RequestCreate requestCreate, String username) {
}

package com.example.cto.model.enums;

import lombok.Getter;

@Getter
public enum RequestStatus {
    ACCEPTED("ACCEPTED", 0),
    IN_PROGRESS("IN PROGRESS", 1),
    REJECTED("REJECTED", 500),
    SUCCESS("SUCCESS", 200);
    private String value;
    private int code;
    private RequestStatus(String value, int code) {
        this.value = value;
        this.code = code;
    }

    public static RequestStatus fromCode(int code) {
        for (RequestStatus status : RequestStatus.values()) {
            if (status.getCode() == code) return status;
        }
        throw new IllegalArgumentException("Unknown RequestStatus code: " + code);
    }

    public static RequestStatus fromValue(String value) {
        for (RequestStatus status : RequestStatus.values()) {
            if (status.getValue() == value) return status;
        }
        throw new IllegalArgumentException("Unknown RequestStatus code: " + value);
    }
}

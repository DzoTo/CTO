package com.example.cto.model;

import com.example.cto.model.converter.RequestStatusConverter;
import com.example.cto.model.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "request_history")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_history_seq")
    @SequenceGenerator(name = "request_history_seq", sequenceName = "request_history_id_sequence", allocationSize = 1)
    private Long id;
    @Convert(converter = RequestStatusConverter.class)
    private RequestStatus status;
    private String message;
    private String workerName;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    private LocalDateTime updatedAt;

    public static RequestHistory buildForUpdate(String msg, RequestStatus newStatus, String workerName, Request request) {
        return RequestHistory.builder()
                .message(msg)
                .status(newStatus)
                .workerName(workerName)
                .request(request)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}


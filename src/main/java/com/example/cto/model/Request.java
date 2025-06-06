package com.example.cto.model;

import com.example.cto.dto.RequestCreate;
import com.example.cto.model.converter.RequestStatusConverter;
import com.example.cto.model.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "requests")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_seq")
    @SequenceGenerator(name = "request_seq", sequenceName = "request_id_sequence", allocationSize = 1)
    private Long id;
    private String title;
    private String content;
    @Convert(converter = RequestStatusConverter.class)
    private RequestStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String contactEmail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "request", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<RequestHistory> history;

    public static Request build(RequestCreate requestCreate, User user) {
        return Request.builder()
                .title(requestCreate.title())
                .user(user)
                .content(requestCreate.description())
                .contactEmail(requestCreate.contactEmail())
                .status(RequestStatus.ACCEPTED)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}

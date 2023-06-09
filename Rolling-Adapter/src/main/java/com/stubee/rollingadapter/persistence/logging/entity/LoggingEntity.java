package com.stubee.rollingadapter.persistence.logging.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_logging")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LoggingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 250)
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    private String module;

    @NotNull
    private UUID memberId;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
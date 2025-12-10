package com.e_learning_platform.enrolment_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "enrolment_table")
public class Enrolment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long enrollmentId;

    @Column
    private Long userId;

    @Column
    private Long courseId;

    @Column
    private LocalDateTime enrolledAt;

    @Column
    private String status;

    @PrePersist
    protected void onCreate() {
        enrolledAt = LocalDateTime.now();
    }
}

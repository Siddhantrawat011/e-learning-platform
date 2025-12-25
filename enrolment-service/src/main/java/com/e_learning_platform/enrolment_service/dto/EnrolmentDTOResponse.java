package com.e_learning_platform.enrolment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrolmentDTOResponse {

    private Long enrolmentId;
    private Long courseId;
    private Long userId;
    private String username;
    private String title;
    private String description;
    private LocalDateTime enrolmentDate;
    private String status; // ENROLLED, COMPLETED, DROPPED
    private Double progressPercentage = 0.0;

}

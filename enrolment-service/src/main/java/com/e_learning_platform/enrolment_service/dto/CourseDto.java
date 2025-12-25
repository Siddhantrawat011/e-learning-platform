package com.e_learning_platform.enrolment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private Long courseId;
    private String title;
    private String description;
    private Double price;
}

package com.e_learning_platform.content_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "lesson")
@Builder
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long lessonId;

    @Column
    @NotBlank
    private String lessonName;

    @Column
    @NotBlank
    private String description;

    @Column
    private Long courseId; // used to map to course-microservice

    @Column
    @NotBlank
    private int orderNumber;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private boolean isPublished;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LearningMaterial> learningMaterialList = new ArrayList<>();

    @PrePersist
    protected void onCreate() {createdAt = LocalDateTime.now();}

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

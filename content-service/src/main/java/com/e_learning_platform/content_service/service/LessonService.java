package com.e_learning_platform.content_service.service;

import com.e_learning_platform.content_service.entity.CourseDTO;
import com.e_learning_platform.content_service.entity.Lesson;
import com.e_learning_platform.content_service.repository.LessonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class LessonService {

    @Value("${course.service.url}")
    private String courseServiceUrl;

    private final LessonRepo lessonRepo;
    private final WebClient.Builder builder;

    public void createLesson(Lesson lesson){
        // 1. check if course exists
        builder.build()
                .get()

    }

    public CourseDTO isCourseValid(Long courseId, String token){
        try {
            return null;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}


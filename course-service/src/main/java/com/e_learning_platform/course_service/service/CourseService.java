package com.e_learning_platform.course_service.service;

import com.e_learning_platform.course_service.entity.Course;
import com.e_learning_platform.course_service.repository.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepo courseRepo;

    public Course createCourse(Course course) {
        return courseRepo.save(course);
    }
}

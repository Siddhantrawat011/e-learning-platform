package com.e_learning_platform.course_service.controller;

import com.e_learning_platform.course_service.entity.Course;
import com.e_learning_platform.course_service.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<Page<Course>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
        return ResponseEntity.ok(courseService.getAllCourses(page, size, sortBy, direction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id){
        System.out.println("Received GET /api/courses/{id}, id=" + id);
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }
}

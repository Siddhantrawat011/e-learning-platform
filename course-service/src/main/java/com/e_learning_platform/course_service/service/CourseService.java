package com.e_learning_platform.course_service.service;

import com.e_learning_platform.course_service.entity.Course;
import com.e_learning_platform.course_service.repository.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService{
    private final CourseRepo courseRepo;

    public Course createCourse(Course course) {
        return courseRepo.save(course);
    }

    public Page<Course> getAllCourses(int page, int size, String sortBy, String direction){
        Sort sort = direction.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        System.out.println("Pagination Object: " + pageable);
        return courseRepo.findAll(pageable);
    }

    public Course getCourseById(Long id) {
        return courseRepo.findById(id).orElseThrow();
    }
}

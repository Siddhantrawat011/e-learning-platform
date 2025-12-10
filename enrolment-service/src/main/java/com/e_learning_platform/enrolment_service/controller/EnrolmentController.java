package com.e_learning_platform.enrolment_service.controller;

import com.e_learning_platform.enrolment_service.entity.Enrolment;
import com.e_learning_platform.enrolment_service.service.EnrolmentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrol")
@RequiredArgsConstructor
public class EnrolmentController {

    private final EnrolmentService enrolmentService;

    @PostMapping("/{userId}/{courseId}")
    public ResponseEntity<Enrolment> enrolInCourse(@RequestHeader("Authorization") String token,
                                                   @PathVariable("userId") @NonNull Long userId,
                                                   @PathVariable("courseId")  @NonNull Long courseId){
        System.out.println("UserId=" + userId + ", CourseID=" + courseId);
        return ResponseEntity.ok(enrolmentService.enrolInCourse(userId, courseId, token));
    }
}

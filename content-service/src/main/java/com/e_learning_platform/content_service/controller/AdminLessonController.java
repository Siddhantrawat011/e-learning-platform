package com.e_learning_platform.content_service.controller;

import com.e_learning_platform.content_service.entity.Lesson;
import com.e_learning_platform.content_service.exception.UnauthorizedException;
import com.e_learning_platform.content_service.service.JwtService;
import com.e_learning_platform.content_service.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.apache.naming.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminLessonController {

    private final LessonService lessonService;
    private final JwtService jwtService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/lessons")
    public void createLesson(@RequestBody Lesson lesson) throws UnauthorizedException{

    }
}



package com.e_learning_platform.content_service.repository;

import com.e_learning_platform.content_service.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepo extends JpaRepository<Lesson, Long> {}

package com.e_learning_platform.content_service.repository;

import com.e_learning_platform.content_service.entity.LearningMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningMaterialRepo extends JpaRepository<LearningMaterial, Long> {}

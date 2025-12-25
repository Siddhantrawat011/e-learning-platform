package com.e_learning_platform.enrolment_service.repository;

import com.e_learning_platform.enrolment_service.entity.Enrolment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrolmentRepo extends JpaRepository<Enrolment, Long> {
    List<Enrolment> findByUserId(Long userId);
}

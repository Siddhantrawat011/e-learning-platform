package com.e_learning_platform.appuser_service.repository;

import com.e_learning_platform.appuser_service.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}

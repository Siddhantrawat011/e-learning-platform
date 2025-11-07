package com.e_learning_platform.appuser_service.service;

import com.e_learning_platform.appuser_service.dto.AppUserDTO;
import com.e_learning_platform.appuser_service.entity.AppUser;
import com.e_learning_platform.appuser_service.entity.Role;
import com.e_learning_platform.appuser_service.repository.AppUserRepo;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepo appUserRepo;
    private final PasswordEncoder passwordEncoder;

    public AppUser registerUser(AppUserDTO userDTO) {
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        AppUser newUser = AppUser.builder()
                .username(userDTO.getUsername())
                .password(encodedPassword)
                .email(userDTO.getEmail())
                .role(userDTO.getRoleOrDefault())
                .build();
        appUserRepo.save(newUser);
        return newUser;
    }

    public List<AppUser> fetchAllUsers() {
        return appUserRepo.findAll();
    }
}

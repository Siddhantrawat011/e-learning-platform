package com.e_learning_platform.appuser_service.service;

import com.e_learning_platform.appuser_service.dto.AppUserDTO;
import com.e_learning_platform.appuser_service.entity.AppUser;
import com.e_learning_platform.appuser_service.repository.AppUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    public void deleteUserById(Long userId) {
        Optional<AppUser> user = appUserRepo.findById(userId);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " does not exist!");
        }
        appUserRepo.deleteById(userId);
    }
}

package com.e_learning_platform.appuser_service.controller;

import com.e_learning_platform.appuser_service.entity.AppUser;
import com.e_learning_platform.appuser_service.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final AppUserService appUserService;

    @GetMapping
    public ResponseEntity<List<AppUser>> getAllUsers(){
        List<AppUser> allUsers = appUserService.fetchAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id){
        AppUser user = appUserService.getUserById(id);
        System.out.println("Received GET /api/users/{id}, id=" + id);
        return ResponseEntity.ok(user);
    }
}

package com.e_learning_platform.appuser_service.controller;

import com.e_learning_platform.appuser_service.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AppUserService appUserService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dashboard")
    public String dashboard(){
        return "Welcome to the admin dashboard!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable long userId){
        appUserService.deleteUserById(userId);
        return ResponseEntity.ok("User with id " + userId + " deleted successfully!");
    }
}

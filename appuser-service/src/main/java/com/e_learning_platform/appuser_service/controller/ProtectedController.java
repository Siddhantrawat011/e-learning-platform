package com.e_learning_platform.appuser_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/protected")
public class ProtectedController {

    @GetMapping
    public String protectedRoute(){
        return "Congo!, You have access to the protected resource!";
    }
}

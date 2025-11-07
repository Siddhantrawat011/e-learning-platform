package com.e_learning_platform.appuser_service.controller;

import com.e_learning_platform.appuser_service.dto.AppUserDTO;
import com.e_learning_platform.appuser_service.dto.LoginRequest;
import com.e_learning_platform.appuser_service.dto.LoginResponse;
import com.e_learning_platform.appuser_service.entity.AppUser;
import com.e_learning_platform.appuser_service.service.AppUserService;
import com.e_learning_platform.appuser_service.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AppUserService appUserService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<AppUser> registerUser(@RequestBody AppUserDTO userDTO){
        AppUser registeredUser = appUserService.registerUser(userDTO);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try {
            // 1. Authenticate the user credentials
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // 2. if the code reaches here, authentication is guaranteed to be successful
            // if successful, generate the jwt token
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(userDetails);

            // 3. return the token in response
            return ResponseEntity.ok(new LoginResponse(token));

        }catch (AuthenticationException e){
            String errorMessage;

            if(e instanceof BadCredentialsException){
                errorMessage = "Incorrect username or password.Please try again!";
            } else{
                errorMessage = "Authentication Failed!";
            }
            // 5. Return a 401 unauthorized error message
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(java.util.Collections.singletonMap("Error", errorMessage));
        }
    }

}

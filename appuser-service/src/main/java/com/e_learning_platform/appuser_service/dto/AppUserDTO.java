package com.e_learning_platform.appuser_service.dto;

import com.e_learning_platform.appuser_service.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDTO {
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 30, message = "Username's length must be between 3 and 30 characters")
    private String username;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    private Role role;

    // Optional: helper method to ensure role is valid
    public Role getRoleOrDefault() {
        return (role == null) ? Role.USER : role;
    }
}

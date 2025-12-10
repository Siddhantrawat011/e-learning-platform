package com.e_learning_platform.enrolment_service.service;

import com.e_learning_platform.enrolment_service.repository.EnrolmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("UserDetails not available in Enrolment Service");
    }
}

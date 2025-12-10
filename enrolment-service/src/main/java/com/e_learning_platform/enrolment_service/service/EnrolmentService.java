package com.e_learning_platform.enrolment_service.service;

import com.e_learning_platform.enrolment_service.dto.CourseDto;
import com.e_learning_platform.enrolment_service.dto.UserDto;
import com.e_learning_platform.enrolment_service.entity.Enrolment;
import com.e_learning_platform.enrolment_service.repository.EnrolmentRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrolmentService {
    private final WebClient.Builder webClientBuilder;
    private final EnrolmentRepo enrolmentRepo;

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Value("${course.service.url}")
    private String courseServiceUrl;

    public UserDto getUserById(Long userId, String token){
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(userServiceUrl + "/api/users/{id}", userId)
                    .header("Authorization", token)
                    .retrieve()
                    .bodyToMono(UserDto.class)
                    .block(); // synchronous
        } catch (WebClientResponseException.NotFound ex){
            System.out.println("[ENROL DEBUG] user not found -> " + ex.getMessage());
            return null;
        } catch (Exception ex) {
            System.out.println("[ENROL DEBUG] unexpected error calling user service: " + ex.getClass().getSimpleName() + " : " + ex.getMessage());
            throw ex;
        }
    }

    public CourseDto getCourseById(Long courseId, String token){
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(courseServiceUrl + "/api/courses/{id}", courseId)
                    .header("Authorization", token)
                    .retrieve()
                    .bodyToMono(CourseDto.class)
                    .block(); // synchronous
        } catch (WebClientResponseException.NotFound ex){
            System.out.println("[ENROL DEBUG] user not found -> " + ex.getMessage());
            return null;
        } catch (Exception ex) {
            System.out.println("[ENROL DEBUG] unexpected error calling course service: " + ex.getClass().getSimpleName() + " : " + ex.getMessage());
            throw ex;
        }
    }

    public @Nullable Enrolment enrolInCourse(@NonNull Long userId, @NonNull Long courseId, String token) {
        UserDto userDto = getUserById(userId, token);
        CourseDto courseDto = getCourseById(courseId, token);
        if(userDto == null){
            throw new IllegalArgumentException("User not found with user id: " + userId);
        }
        if(courseDto == null){
            throw new IllegalArgumentException("Course not found with course id: " + courseId);
        }

        // optionally check duplicates
        Optional<Enrolment> existing = enrolmentRepo.findAll()
                .stream().filter(e -> e.getCourseId().equals(courseId) && e.getUserId().equals(userId))
                .findFirst();
        if(existing.isPresent()){
            return existing.get();
        }

        Enrolment enrolment = new Enrolment();
        enrolment.setCourseId(courseId);
        enrolment.setUserId(userId);
        enrolment.setStatus("Enrolled");
        return enrolmentRepo.save(enrolment);
    }

}

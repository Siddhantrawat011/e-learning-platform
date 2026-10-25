package com.e_learning_platform.content_service.exception;

import com.e_learning_platform.content_service.error.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorMessage> handleException(UnauthorizedException ex, HttpServletRequest request){
        String message = "You are not authorized to perform this action.";
        return buildResponse(HttpStatus.UNAUTHORIZED, message, request);
    }

    public ResponseEntity<ErrorMessage> buildResponse(HttpStatus status, String message, HttpServletRequest request){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .timeStamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(errorMessage, status);
    }
}

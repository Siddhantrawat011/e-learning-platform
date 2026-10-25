package com.e_learning_platform.content_service.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // hides null fields
public class ErrorMessage {
    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private String message;
    private String path;


}

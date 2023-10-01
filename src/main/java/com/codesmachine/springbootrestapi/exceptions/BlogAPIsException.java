package com.codesmachine.springbootrestapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

public class BlogAPIsException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public BlogAPIsException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public BlogAPIsException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

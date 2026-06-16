package com.example.Immigration.Management.System.RESTful.API.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private Date timestamp;
    private int statusCode;
    private HttpStatus status;
    private String error;
    private String message;
    private String path;

    public ErrorResponse(int statusCode, HttpStatus status, String message, String error, String path) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.error = error;
        this.path = path;
    }

}

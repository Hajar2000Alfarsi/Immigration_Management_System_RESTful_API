package com.example.Immigration.Management.System.RESTful.API.Exception;

import org.springframework.http.HttpStatus;

public class InterviewException extends RuntimeException{
    private final HttpStatus status;

    public InterviewException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public InterviewException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    //ID not Found
    public static InterviewException IdNotFound(Long id){
        return new InterviewException(
                "Interview with ID " + id + " was not found.",
                HttpStatus.NOT_FOUND
        );
    }

    public static InterviewException doubleBooked(){
        return new InterviewException(
                "Officer is double-booked!",
                HttpStatus.CONFLICT
        );
    }
}

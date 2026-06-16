package com.example.Immigration.Management.System.RESTful.API.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class CenterException extends RuntimeException{
    private final HttpStatus status;

    public CenterException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public CenterException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    //name missing
    public static CenterException nameMissing(){
        return new CenterException(
                "Center name Required",
                HttpStatus.BAD_REQUEST
        );
    }

    //ID not Found
    public static CenterException IdNotFound(Long id){
        return new CenterException(
                "Center with ID " + id + " was not found.",
                HttpStatus.NOT_FOUND
        );
    }
}

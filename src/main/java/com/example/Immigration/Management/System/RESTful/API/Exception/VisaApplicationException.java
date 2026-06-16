package com.example.Immigration.Management.System.RESTful.API.Exception;

import org.springframework.http.HttpStatus;

public class VisaApplicationException extends RuntimeException{
    public final HttpStatus status;

    public VisaApplicationException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public VisaApplicationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    //visa type missing
    public static VisaApplicationException visaTypeMissing(){
        return new VisaApplicationException(
                "Visa type Required",
                HttpStatus.BAD_REQUEST);
    }
}

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

    //ID not Found
    public static VisaApplicationException IdNotFound(Long id){
        return new VisaApplicationException(
                "Visa Application with ID " + id + " was not found.",
                HttpStatus.NOT_FOUND
        );
    }

    //insufficient Clearance
    public static VisaApplicationException insufficientClearance(String level){
        return new VisaApplicationException(
                "Clearance level " + level + " is not enough for Asylum visa (required: 4 or 5)",
                HttpStatus.FORBIDDEN
        );
    }

}

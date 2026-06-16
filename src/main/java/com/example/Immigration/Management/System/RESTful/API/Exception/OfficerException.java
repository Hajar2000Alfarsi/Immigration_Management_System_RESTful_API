package com.example.Immigration.Management.System.RESTful.API.Exception;

import com.example.Immigration.Management.System.RESTful.API.Services.OfficerService;
import org.springframework.http.HttpStatus;

public class OfficerException extends RuntimeException{
    private final HttpStatus status;

    public OfficerException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public OfficerException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    //Invalid clearance
    public static OfficerException invalidClearance(){
        return new OfficerException(
                "Clearance must be between 1 and 5",
                HttpStatus.BAD_REQUEST
        );
    }

    //ID not Found
    public static OfficerException IdNotFound(Long id){
        return new OfficerException(
                "Officer with ID " + id + " was not found.",
                HttpStatus.NOT_FOUND
        );
    }

    //Rank missing
    public static OfficerException rankMissing(){
        return new OfficerException(
                "Officer rank Required",
                HttpStatus.BAD_REQUEST
        );
    }

    //Badge Number missing
    public static OfficerException badgeNumberMissing(){
        return new OfficerException(
                "Badge Number Required",
                HttpStatus.BAD_REQUEST
        );
    }

    public HttpStatus getStatus() {
        return status;
    }

}

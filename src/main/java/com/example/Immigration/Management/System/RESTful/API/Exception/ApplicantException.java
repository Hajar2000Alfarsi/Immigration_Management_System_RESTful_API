package com.example.Immigration.Management.System.RESTful.API.Exception;

import org.springframework.http.HttpStatus;

public class ApplicantException extends RuntimeException{
    private final HttpStatus status;

    public ApplicantException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public ApplicantException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    //ID Invalid
    public static ApplicantException invalidId(Long id){
        return new ApplicantException(
                "Applicant with ID " + id + " was not found.",
                HttpStatus.NOT_FOUND
        );
    }

    //ID required
    public static ApplicantException idNotFound(){
        return new ApplicantException(
                "Applicant Id Required",
                HttpStatus.NOT_FOUND
        );
    }

    //First and Last name not found
    public static ApplicantException nameNotFound(){
        return new ApplicantException(
                "First and Last name required",
                HttpStatus.NOT_FOUND
        );
    }

    //Invalid Passport
    public static ApplicantException invalidPassport() {
        return new ApplicantException(
                "Invalid passport number",
                HttpStatus.BAD_REQUEST
        );
    }

    //Nationality not found
    public static ApplicantException nationalityNotFound(){
        return new ApplicantException(
                "Nationality required",
                HttpStatus.NOT_FOUND
        );
    }
    //Bad Request
    public static ApplicantException badRequest(String message) {
        return  new ApplicantException(
                message,HttpStatus.BAD_REQUEST
        );
    }


}

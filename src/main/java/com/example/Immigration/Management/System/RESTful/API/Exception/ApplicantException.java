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

    //ID not Found
    public static ApplicantException IdNotFound(Long id){
        return new ApplicantException(
                "Applicant with ID " + id + " was not found.",
                HttpStatus.NOT_FOUND
        );
    }

    //ID missing
    public static ApplicantException idMissing(){
        return new ApplicantException(
                "Applicant Id Required",
                HttpStatus.BAD_REQUEST
        );
    }

    //First and Last name Missing
    public static ApplicantException nameMissing(){
        return new ApplicantException(
                "First and Last name required",
                HttpStatus.BAD_REQUEST
        );
    }

    //Invalid Passport
    public static ApplicantException invalidPassport() {
        return new ApplicantException(
                "Invalid passport number",
                HttpStatus.BAD_REQUEST
        );
    }

    //Nationality missing
    public static ApplicantException nationalityMissing(){
        return new ApplicantException(
                "Nationality required",
                HttpStatus.BAD_REQUEST
        );
    }
    //Bad Request
    public static ApplicantException badRequest(String message) {
        return  new ApplicantException(
                message,HttpStatus.BAD_REQUEST
        );
    }

    public HttpStatus getStatus() {
        return status;
    }

}

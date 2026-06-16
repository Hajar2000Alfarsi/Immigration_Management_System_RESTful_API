package com.example.Immigration.Management.System.RESTful.API.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //404 not found

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(GenericException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                "Not found",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", ""));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request){
        ex.printStackTrace();

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                ex.getClass().getName(),
                request.getDescription(false).replace("uri=",""));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(CenterException.class)
    public ResponseEntity<ErrorResponse> handleCenterException(
            CenterException ex,
            WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                "Center Eroor",
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ApplicantException.class)
    public ResponseEntity<ErrorResponse> handleApplicantException(
            ApplicantException ex,
            WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getStatus().value(),
                ex.getStatus(),
                ex.getMessage(),
                "Applicant Error",
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(ex.getStatus()).body(errorResponse);
    }
}

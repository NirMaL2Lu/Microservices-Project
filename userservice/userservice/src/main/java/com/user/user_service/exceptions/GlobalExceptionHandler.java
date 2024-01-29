package com.user.user_service.exceptions;

import com.user.user_service.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerUserNotFoundException(UserNotFoundException exception){

        String message = exception.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
        System.out.println("Response :"+response);
        return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);


    }
}

package com.books.exceptions.handler;

import com.books.exceptions.ResourceNotFound;
import com.books.exceptions.UserNotAdminException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotAdminException.class)
    public ResponseEntity<Object> handle(UserNotAdminException ex) {
        String message = ex.getMessage();


        Map<String, Object> body = new HashMap<>();
        body.put("Message: ", message);
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Object> handleresource(ResourceNotFound ex) {
        String message = ex.getMessage();


        Map<String, Object> body = new HashMap<>();
        body.put("Message: ", message);
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package com.kbm.instagram.advice;

import com.kbm.instagram.exception.FeedNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FeedNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(FeedNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(FeedNotFoundException ex) {
        return ex.getMessage();
    }
}

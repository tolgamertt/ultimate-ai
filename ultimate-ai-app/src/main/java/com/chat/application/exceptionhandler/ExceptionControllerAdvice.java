package com.chat.application.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    private static final String ERROR_MESSAGE = "Something went wrong. I am not able to process your request at the moment. You will be transferred to the agent queue automatically!";

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Object> exception(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
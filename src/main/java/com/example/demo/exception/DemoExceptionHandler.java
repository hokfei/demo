package com.example.demo.exception;

import com.example.demo.model.DemoResponseModel;
import java.time.LocalDateTime;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class DemoExceptionHandler {
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<Object> handleAnyException(Exception exception, WebRequest webRequest) {
        DemoResponseModel res = new DemoResponseModel();
        res.setMessage("error.500");
        res.setTime(LocalDateTime.now());
        log.error("An exception occured {}", exception);
        return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException exception) {
        DemoResponseModel res = new DemoResponseModel();
        res.setMessage("error.404");
        res.setTime(LocalDateTime.now());
        log.error("An entity not found exception occured {}", exception);
        return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception) {
        DemoResponseModel res = new DemoResponseModel();
        res.setMessage(exception.getMessage() != null? exception.getMessage() : "error.500");
        res.setTime(LocalDateTime.now());
        log.error("A cumstom exception occured {}", exception);
        return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

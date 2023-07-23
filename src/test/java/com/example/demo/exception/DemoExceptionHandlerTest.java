package com.example.demo.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.model.DemoResponseModel;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class DemoExceptionHandlerTest {
    DemoExceptionHandler handler;

    @BeforeEach
    void setup() {
        handler = new DemoExceptionHandler();
    }

    @Test
    void testAnyException() {
        ResponseEntity<Object> result = handler.handleAnyException(new Exception(), null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        DemoResponseModel resultBody = (DemoResponseModel) result.getBody();
        assertEquals("error.500", resultBody.getMessage());
    }

    @Test
    void testEntityNotFound() {
        ResponseEntity<Object> result = handler.handleEntityNotFound(new EntityNotFoundException());
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        DemoResponseModel resultBody = (DemoResponseModel) result.getBody();
        assertEquals("error.404", resultBody.getMessage());
    }

    @Test
    void testIllegalArgumentExceptionNoMsg() {
        ResponseEntity<Object> result = handler.handleIllegalArgumentException(new IllegalArgumentException());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        DemoResponseModel resultBody = (DemoResponseModel) result.getBody();
        assertEquals("error.500", resultBody.getMessage());
    }

    @Test
    void testIllegalArgumentExceptionWithMsg() {
        ResponseEntity<Object> result = handler.handleIllegalArgumentException(new IllegalArgumentException("test1"));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        DemoResponseModel resultBody = (DemoResponseModel) result.getBody();
        assertEquals("test1", resultBody.getMessage());
    }
}

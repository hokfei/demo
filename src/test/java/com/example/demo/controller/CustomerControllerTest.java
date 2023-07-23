package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.demo.DummyDataUtil;
import com.example.demo.model.CustomerModel;
import com.example.demo.service.CustomerService;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class CustomerControllerTest {
    private CustomerController controller;
    private CustomerService service;
    
    @BeforeEach
    void setup() {
        service = mock(CustomerService.class);
        controller = new CustomerController(service);
    }
    
    @Test
    void testCreateCustomerSuccess() {
        CustomerModel model = DummyDataUtil.getCustomerModel();
        when(service.createCustomer(model)).thenReturn(model);
        ResponseEntity<CustomerModel> result = controller.createCustomer(model);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(model, result.getBody());
    }
    
    @Test
    void testCreateCustomerNull() {
        when(service.createCustomer(null)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> controller.createCustomer(null));
    }
    
    @Test
    void testGetCustomer() {
        Long customerId = 1L;
        CustomerModel model = DummyDataUtil.getCustomerModel();
        when(service.getOneCustomer(customerId)).thenReturn(model);
        ResponseEntity<CustomerModel> result = controller.getOneCustomer(customerId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(model, result.getBody());
    }
    
    @Test
    void testGetCustomerError() {
        when(service.getOneCustomer(null)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> controller.getOneCustomer(null));
   
    }
}

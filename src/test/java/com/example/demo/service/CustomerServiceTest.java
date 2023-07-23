package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.demo.DummyDataUtil;
import com.example.demo.entity.Customer;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.model.CustomerModel;
import com.example.demo.repository.CustomerRepository;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerServiceTest {

    private CustomerService service;
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    
    @BeforeEach
    void setup() {
        customerRepository = mock(CustomerRepository.class);
        customerMapper = mock(CustomerMapper.class);
        service = new CustomerService(customerRepository, customerMapper);
    }
    
    @Test
    void testGetOneCustomerPass() {
        Long customerId = 1L;
        Customer customer = DummyDataUtil.getCustomer();
        CustomerModel customerModel = DummyDataUtil.getCustomerModel();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerMapper.toModel(customer)).thenReturn(customerModel);
        CustomerModel result = service.getOneCustomer(customerId);
        assertEquals(customerModel, result);
    }
    
    @Test
    void testGetOneCustomerNotFound() {
        assertThrows(EntityNotFoundException.class, () -> service.getOneCustomer(null));
    }
    
    @Test
    void testCreateCustomer() {
        Customer customer = DummyDataUtil.getCustomer();
        CustomerModel customerModel = DummyDataUtil.getCustomerModel();
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toEntity(customerModel)).thenReturn(customer);
        when(customerMapper.toModel(customer)).thenReturn(customerModel);
        CustomerModel result = service.createCustomer(customerModel);
        assertEquals(customerModel, result);
    }
}

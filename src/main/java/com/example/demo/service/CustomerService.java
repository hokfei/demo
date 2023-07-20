package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.model.CustomerModel;
import com.example.demo.repository.CustomerRepository;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    
    public CustomerModel createCustomer(CustomerModel model) {
        Customer customer = customerRepository.save(customerMapper.toEntity(model));
        return customerMapper.toModel(customer);
    }
    
    public CustomerModel getOneCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            Customer c = customer.get();
             return customerMapper.toModel(c);
        } else {
            throw new EntityNotFoundException();
        }
        
    }
}

package com.example.demo.controller;

import com.example.demo.model.CustomerModel;
import com.example.demo.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/customer")
public class CustomerController {
    private CustomerService customerService;
    
    @ApiOperation(value = "Get 1 customer", response = String.class,
            tags = "")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @GetMapping
    public ResponseEntity<CustomerModel> testReturn(@RequestParam("id") Long id) {
        return ResponseEntity.ok(customerService.getOneCustomer(id));
    }
    
    @ApiOperation(value = "Create 1 customer", response = String.class,
            tags = "")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @PostMapping
    public ResponseEntity<CustomerModel> createCustomer(@RequestBody CustomerModel customerModel) {
        return ResponseEntity.ok(customerService.createCustomer(customerModel));
    }
}

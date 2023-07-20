package com.example.demo.mapper;

import com.example.demo.entity.Customer;
import com.example.demo.model.CustomerModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CustomerModel model);
    CustomerModel toModel(Customer entity);
}

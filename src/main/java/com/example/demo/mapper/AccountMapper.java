package com.example.demo.mapper;

import com.example.demo.entity.Account;
import com.example.demo.model.AccountModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toEntity(AccountModel model);
    
    @Mapping(source = "customer.id", target = "customerId")
    AccountModel toModel(Account entity);
}

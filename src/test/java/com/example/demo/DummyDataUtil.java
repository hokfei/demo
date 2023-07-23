package com.example.demo;

import com.example.demo.model.AccountModel;
import com.example.demo.model.CustomerModel;
import java.math.BigDecimal;

public class DummyDataUtil {
    public static AccountModel getAccountModel() {
        AccountModel model = new AccountModel();
        model.setAccountNumber(1234L);
        model.setBalance(new BigDecimal("2345"));
        model.setCustomerId(3456L);
        model.setStatus("aoeu");
        model.setType("oeui");
        model.setVersion(new Integer("1"));
        return model;
    }
    
    public static CustomerModel getCustomerModel() {
        CustomerModel model = new CustomerModel();
        model.setId(4567L);
        model.setName("euid");
        model.setVersion(new Integer("2"));
        return model;
    }
}

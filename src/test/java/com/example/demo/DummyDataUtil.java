package com.example.demo;

import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
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
        model.setVersion(1);
        return model;
    }
    
    public static CustomerModel getCustomerModel() {
        CustomerModel model = new CustomerModel();
        model.setId(4567L);
        model.setName("euid");
        model.setVersion(2);
        return model;
    }

    public static Account getAccount() {
        Account account = new Account();
        account.setAccountNumber(1234L);
        account.setBalance(new BigDecimal("2345"));
        account.setStatus("aoeu");
        account.setType("oeui");
        account.setVersion(1);
        return account;
    }

	public static Customer getCustomer() {
		Customer customer = new Customer();
        customer.setId(4567L);
        customer.setName("euid");
        customer.setVersion(2);
        return customer;
	}
}

package com.example.demo.controller;

import static org.mockito.Mockito.mock;

import com.example.demo.DummyDataUtil;
import com.example.demo.model.AccountModel;
import com.example.demo.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class AccountControllerTest {

    private AccountController controller;
    private AccountService service;
    
    @BeforeEach
    void setup() {
        service = mock(AccountService.class);
        controller = new AccountController(service);
    }
    
    @Test
    void test1() {
        AccountModel model = DummyDataUtil.getAccountModel();
        ResponseEntity<AccountModel> result = controller.createAccount(model);
        //assertTrue();
    }
    
}

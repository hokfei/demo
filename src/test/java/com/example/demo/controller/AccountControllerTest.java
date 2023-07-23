package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.demo.DummyDataUtil;
import com.example.demo.model.AccountModel;
import com.example.demo.service.AccountService;
import java.math.BigDecimal;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
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
    void testCreateAccount() {
        AccountModel model = DummyDataUtil.getAccountModel();
        when(service.createAccount(model)).thenReturn(model);
        ResponseEntity<AccountModel> result = controller.createAccount(model);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(model, result.getBody());
    }

    @Test
    void testGetAccountNull() {
        Long accNum = 1111L;
        when(service.getAccount(accNum)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> controller.getAccount(accNum));
    }

    @Test
    void testGetAccount1234() {
        AccountModel model = DummyDataUtil.getAccountModel();
        Long accNum = 1234L;
        when(service.getAccount(accNum)).thenReturn(model);
        ResponseEntity<AccountModel> result = controller.getAccount(accNum);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(model, result.getBody());
    }

    @Test
    void testDepositMoney() {
        Long accountNumber = 1234L;
        BigDecimal amount = BigDecimal.ZERO;
        AccountModel accountModel = DummyDataUtil.getAccountModel();
        when(service.depositMoney(accountNumber, amount)).thenReturn(accountModel);
        ResponseEntity<AccountModel> result = controller.depositMoney(accountNumber, amount);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(accountModel, result.getBody());
    }

    @Test
    void testWithdrawMoney() {
        Long accountNumber = 1234L;
        BigDecimal amount = BigDecimal.ZERO;
        AccountModel accountModel = DummyDataUtil.getAccountModel();
        when(service.withdrawMoney(accountNumber, amount)).thenReturn(accountModel);
        ResponseEntity<AccountModel> result = controller.withdrawMoney(accountNumber, amount);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(accountModel, result.getBody());
    }

    @Test
    void testCloseAccount() {
        Long accountNumber = 1234L;
        AccountModel accountModel = DummyDataUtil.getAccountModel();
        when(service.closeAccount(accountNumber)).thenReturn(accountModel);
        ResponseEntity<AccountModel> result = controller.closeAccount(accountNumber);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(accountModel, result.getBody());
    }
}

package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.demo.DummyDataUtil;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.model.AccountModel;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import java.math.BigDecimal;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountServiceTest {

    private AccountService accountService;
    private AccountRepository accountRepository;
    private AccountMapper accountMapper;
    private CustomerRepository customerRepository;

    @BeforeEach
    void setup() {
        accountRepository = mock(AccountRepository.class);
        accountMapper = mock(AccountMapper.class);
        customerRepository = mock(CustomerRepository.class);
        accountService = new AccountService(accountRepository, accountMapper, customerRepository);
    }

    @Test
    void testCreateAccount() {
        AccountModel accountModel = DummyDataUtil.getAccountModel();
        Customer customer = DummyDataUtil.getCustomer();
        Account account = new Account();
        account.setStatus("Active");
        account.setCustomer(customer);
        account.setType(accountModel.getType());
        account.setBalance(new BigDecimal("0"));
        when(customerRepository.findById(3456L)).thenReturn(Optional.of(customer));
        when(accountMapper.toModel(account)).thenReturn(accountModel);
        AccountModel result = accountService.createAccount(accountModel);
        assertNotNull(result);
    }

    @Test
    void testCreateAccountNotPresent() {
        assertThrows(EntityNotFoundException.class, () -> accountService.createAccount(new AccountModel()));
    }

    @Test
    void testCreateAccountNull() {
        assertThrows(IllegalArgumentException.class, () -> accountService.createAccount(null));
    }

    @Test
    void testGetAccount() {
        Long accountNumber = 1234L;
        Account account = DummyDataUtil.getAccount();
        AccountModel accountModel = DummyDataUtil.getAccountModel();
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));
        when(accountMapper.toModel(account)).thenReturn(accountModel);
        AccountModel result = accountService.getAccount(accountNumber);
        assertEquals(account.getAccountNumber(), result.getAccountNumber());
        assertEquals(account.getBalance(), result.getBalance());
    }

    @Test
    void testGetAccountNotPresent() {
        assertThrows(EntityNotFoundException.class, () -> accountService.getAccount(1L));
    }

    @Test
    void testDepositMoney() {
        Long accountNumber = 1234L;
        BigDecimal amount = new BigDecimal("234.5");
        AccountModel accountModel = DummyDataUtil.getAccountModel();
        Account account = DummyDataUtil.getAccount();
        Account accountAfter = account;
        accountAfter.getBalance().add(amount);
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));
        when(accountMapper.toModel(accountAfter)).thenReturn(accountModel);
        AccountModel result = accountService.depositMoney(accountNumber, amount);
        assertEquals(accountModel, result);
    }

    @Test
    void testDepositMoneyAccountNotPresent() {
        assertThrows(EntityNotFoundException.class, () -> accountService.depositMoney(1L, null));
    }

    @Test
    void testDepositMoneyNull() {
        Long accountNumber = 1234L;
        Account account = DummyDataUtil.getAccount();
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> accountService.depositMoney(accountNumber, null));
        assertEquals("error.amount.blank", exception.getMessage());
    }

    @Test
    void testDepositMoneyAccountClosed() {
        Long accountNumber = 1234L;
        BigDecimal amount = new BigDecimal("234.5");
        Account account = DummyDataUtil.getAccount();
        account.setStatus("Closed");
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> accountService.depositMoney(accountNumber, amount));
        assertEquals("error.account.closed", exception.getMessage());
    }

    @Test
    void testWithdrawMoney() {
        Long accountNumber = 1234L;
        BigDecimal amount = new BigDecimal("234.5");
        Account account = DummyDataUtil.getAccount();
        Account accountAfter = account;
        accountAfter.getBalance().subtract(amount);
        AccountModel accountModel = DummyDataUtil.getAccountModel();
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));
        when(accountMapper.toModel(accountAfter)).thenReturn(accountModel);
        AccountModel result = accountService.withdrawMoney(accountNumber, amount);
        assertEquals(accountModel, result);
    }

    @Test
    void testWithdrawMoneyAccountNotPresent() {
        assertThrows(EntityNotFoundException.class, () -> accountService.withdrawMoney(1L, null));
    }

    @Test
    void testWithdrawMoneyNull() {
        Long accountNumber = 1234L;
        Account account = DummyDataUtil.getAccount();
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> accountService.withdrawMoney(accountNumber, null));
        assertEquals("error.amount.blank", exception.getMessage());
    }

    @Test
    void testWithdrawMoneyAccountClosed() {
        Long accountNumber = 1234L;
        BigDecimal amount = new BigDecimal("234.5");
        Account account = DummyDataUtil.getAccount();
        account.setStatus("Closed");
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> accountService.withdrawMoney(accountNumber, amount));
        assertEquals("error.account.closed", exception.getMessage());
    }

    @Test
    void testWithdrawMoneyInsufficientBalance() {
        Long accountNumber = 1234L;
        BigDecimal amount = new BigDecimal("2346");
        Account account = DummyDataUtil.getAccount();
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> accountService.withdrawMoney(accountNumber, amount));
        assertEquals("error.insufficient.balance", exception.getMessage());
    }

    @Test
    void testCloseAccount() {
        Long accountNumber = 1234L;
        AccountModel accountModel = DummyDataUtil.getAccountModel();
        Account account = DummyDataUtil.getAccount();
        Account accountAfter = account;
        accountAfter.setStatus("Closed");
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));
        when(accountMapper.toModel(accountAfter)).thenReturn(accountModel);
        AccountModel result = accountService.closeAccount(accountNumber);
        assertEquals(accountModel, result);
    }

    @Test
    void testCloseAccountNotPresent() {
        assertThrows(EntityNotFoundException.class, () -> accountService.closeAccount(1L));
    }
}

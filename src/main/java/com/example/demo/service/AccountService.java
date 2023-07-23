package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.model.AccountModel;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import java.math.BigDecimal;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AccountService {
    private AccountRepository accountRepository;
    private AccountMapper accountMapper;
    private CustomerRepository customerRepository;
    
    public AccountModel createAccount(AccountModel accountModel) {
        if (accountModel == null) {
            throw new IllegalArgumentException();
        }
        Optional<Customer> customer = customerRepository.findById(accountModel.getCustomerId());
        if (customer.isPresent()) {
            Account account = new Account();
            account.setStatus("Active");
            account.setCustomer(customer.get());
            account.setType(accountModel.getType());
            account.setBalance(new BigDecimal("0"));
            accountRepository.save(account);
            return accountMapper.toModel(account);
        } else {
            throw new EntityNotFoundException();
        }
    }
    
    public AccountModel getAccount(Long accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (account.isPresent()) {
            return accountMapper.toModel(account.get());
        } else {
            throw new EntityNotFoundException();
        }
    }
    
    public AccountModel depositMoney(Long accountNumber, BigDecimal amount) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (account.isPresent()) {
            if (amount == null) {
                throw new IllegalArgumentException("error.amount.blank");
            }
            Account a = account.get();
            if ("Closed".equals(a.getStatus())) {
                throw new IllegalArgumentException("error.account.closed");
            }
            a.setBalance(a.getBalance().add(amount));
            accountRepository.save(a);
            return accountMapper.toModel(a);
        } else {
            throw new EntityNotFoundException();
        }
    }
    
    public AccountModel withdrawMoney(Long accountNumber, BigDecimal amount) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (account.isPresent()) {
            if (amount == null) {
                throw new IllegalArgumentException("error.amount.blank");
            }
            Account a = account.get();
            if (a.getBalance().compareTo(amount) < 0) {
                throw new IllegalArgumentException("error.insufficient.balance");
            }
            if ("Closed".equals(a.getStatus())) {
                throw new IllegalArgumentException("error.account.closed");
            }
            a.setBalance(a.getBalance().subtract(amount));
            accountRepository.save(a);
            return accountMapper.toModel(a);
        } else {
            throw new EntityNotFoundException();
        }
    }
    
    public AccountModel closeAccount(Long accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (account.isPresent()) {
            Account a = account.get();
            a.setStatus("Closed");
            accountRepository.save(a);
            return accountMapper.toModel(a);
        } else {
            throw new EntityNotFoundException();
        }
    }
}

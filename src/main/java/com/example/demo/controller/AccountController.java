package com.example.demo.controller;

import com.example.demo.model.AccountModel;
import com.example.demo.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account")
@AllArgsConstructor
public class AccountController {
    private AccountService accountService;
    
    @ApiOperation(value = "Create 1 account", response = AccountModel.class,
            tags = "")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @PostMapping
    public ResponseEntity<AccountModel> createAccount(@RequestBody AccountModel accountModel) {
        return ResponseEntity.ok(accountService.createAccount(accountModel));
    }

    @GetMapping
    public ResponseEntity<AccountModel> getAccount(@RequestParam("accountNumber") Long accountNumber) {
        return ResponseEntity.ok(accountService.getAccount(accountNumber));
    }
    
    @PutMapping(value = "/{accountNumber}/deposit")
    public ResponseEntity<AccountModel> depositMoney(@PathVariable("accountNumber") Long accountNumber, @RequestParam("amount") BigDecimal amount) {
        return ResponseEntity.ok(accountService.depositMoney(accountNumber, amount));
    }
    
    @PutMapping(value = "/{accountNumber}/withdraw")
    public ResponseEntity<AccountModel> withdrawMoney(@PathVariable("accountNumber") Long accountNumber, @RequestParam("amount") BigDecimal amount) {
        return ResponseEntity.ok(accountService.withdrawMoney(accountNumber, amount));
    }
    
    @PostMapping(value = "/{accountNumber}/close")
    public ResponseEntity<AccountModel> closeAccount(@PathVariable("accountNumber") Long accountNumber) {
        return ResponseEntity.ok(accountService.closeAccount(accountNumber));
    }
}

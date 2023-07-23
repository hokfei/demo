package com.example.demo.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountModel {
    private Long accountNumber;
    private String status;
    private Integer version;
    private BigDecimal balance;
    private String type;
    private Long customerId;
}

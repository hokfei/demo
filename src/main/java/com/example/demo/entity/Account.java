package com.example.demo.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accounNumber")
    private Long accountNumber;
    
    @Column(name = "status")
    private String status;
    
    @Version
    @Column(name = "version")
    private Integer version;
    
    @Column(name = "balance")
    private BigDecimal balance;
    
    @Column(name = "type")
    private String type;
    
    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Customer customer;
}

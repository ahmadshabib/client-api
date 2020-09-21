package com.openpayd.clientapi.account.entity;


import com.openpayd.clientapi.client.entity.ClientEntity;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table
@Entity
@Data
public class AccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private BigDecimal balance = BigDecimal.valueOf(0);

  @Column(nullable = false)
  private BalanceStatus status;

  @Column(nullable = false)
  private AccountType type;

  @ManyToOne(cascade = {CascadeType.ALL})
  private ClientEntity clientEntity;

  private LocalDateTime createdAt = LocalDateTime.now();
}

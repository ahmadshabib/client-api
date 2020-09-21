package com.openpayd.clientapi.transaction.entity;

import com.openpayd.clientapi.account.entity.AccountEntity;
import lombok.Data;

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
public class TransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @ManyToOne(optional = false)
  private AccountEntity debitAccountEntity;

  @ManyToOne(optional = false)
  private AccountEntity creditAccountEntity;

  @Column(nullable = false)
  private BigDecimal amount;

  private String message;

  private LocalDateTime createdAt = LocalDateTime.now();
}
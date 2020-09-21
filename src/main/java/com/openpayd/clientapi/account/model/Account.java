package com.openpayd.clientapi.account.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openpayd.clientapi.account.entity.AccountType;
import com.openpayd.clientapi.account.entity.BalanceStatus;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class Account {

  @JsonCreator
  public Account(@JsonProperty("clientId") Long clientId, @JsonProperty("accountType") AccountType accountType, @JsonProperty("balance") BigDecimal balance, @JsonProperty("balanceStatus") BalanceStatus balanceStatus) {
    this.clientId = clientId;
    this.accountType = accountType;
    this.balance = balance;
    this.balanceStatus = balanceStatus;
  }

  @NotNull
  private Long clientId;

  @NotNull
  private AccountType accountType;

  @NotNull
  private BigDecimal balance;

  @NotNull
  private BalanceStatus balanceStatus;
}

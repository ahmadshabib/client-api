package com.openpayd.clientapi.account.model;

import com.openpayd.clientapi.account.entity.AccountType;
import com.openpayd.clientapi.account.entity.BalanceStatus;
import com.openpayd.clientapi.common.model.AbstractBo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
public class AccountBo extends AbstractBo {
  @NotNull
  private Long clientId;

  @NotNull
  private AccountType accountType;

  @NotNull
  private BigDecimal balance;

  @NotNull
  private BalanceStatus balanceStatus;
}

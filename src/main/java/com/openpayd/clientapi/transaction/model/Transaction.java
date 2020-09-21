package com.openpayd.clientapi.transaction.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Transaction {

  @JsonCreator
  public Transaction(
      @JsonProperty("debitAccount") Long debitAccount,
      @JsonProperty("creditAccount") Long creditAccount,
      @JsonProperty("amount") BigDecimal amount,
      @JsonProperty("message") String message) {
    this.debitAccount = debitAccount;
    this.creditAccount = creditAccount;
    this.amount = amount;
    this.message = message;
  }

  @NotNull private Long debitAccount;

  @NotNull private Long creditAccount;

  @NotNull private BigDecimal amount;

  private String message = "";

  private Long id;

  private LocalDateTime createdAt;
}

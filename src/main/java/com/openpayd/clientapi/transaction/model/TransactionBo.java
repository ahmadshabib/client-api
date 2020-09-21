package com.openpayd.clientapi.transaction.model;

import com.openpayd.clientapi.common.model.AbstractBo;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class TransactionBo extends AbstractBo {

  @NotNull private Long id;

  @NotNull private Long debitAccount;

  @NotNull private Long creditAccount;

  @NotNull private BigDecimal amount;

  private String message;

  private LocalDateTime createdAt;
}

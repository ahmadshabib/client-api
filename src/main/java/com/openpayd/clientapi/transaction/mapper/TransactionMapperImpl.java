package com.openpayd.clientapi.transaction.mapper;

import com.openpayd.clientapi.common.mapper.AbstractBoMapper;
import com.openpayd.clientapi.transaction.model.Transaction;
import com.openpayd.clientapi.transaction.model.TransactionBo;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapperImpl extends AbstractBoMapper<Transaction, TransactionBo> {

  @Override
  public Transaction convert(TransactionBo transactionBo) {
    return new Transaction(transactionBo.getDebitAccount(), transactionBo.getCreditAccount(), transactionBo.getAmount(), transactionBo.getMessage());
  }

  @Override
  public TransactionBo convert(Transaction transaction) {
    return TransactionBo.builder()
            .amount(transaction.getAmount())
            .creditAccount(transaction.getCreditAccount())
            .debitAccount(transaction.getDebitAccount())
            .message(transaction.getMessage())
            .id(transaction.getId())
            .createdAt(transaction.getCreatedAt())
            .build();
  }
}

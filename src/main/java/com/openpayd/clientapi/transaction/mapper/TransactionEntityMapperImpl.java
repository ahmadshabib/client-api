package com.openpayd.clientapi.transaction.mapper;

import com.openpayd.clientapi.common.mapper.AbstractBoMapper;
import com.openpayd.clientapi.transaction.entity.TransactionEntity;
import com.openpayd.clientapi.transaction.model.TransactionBo;
import org.springframework.stereotype.Component;

@Component
public class TransactionEntityMapperImpl
    extends AbstractBoMapper<TransactionEntity, TransactionBo> {
  @Override
  public TransactionEntity convert(TransactionBo transactionBo) {
    TransactionEntity transactionEntity = new TransactionEntity();
    transactionEntity.setAmount(transactionBo.getAmount());
    transactionEntity.setMessage(transactionBo.getMessage());
    return transactionEntity;
  }

  @Override
  public TransactionBo convert(TransactionEntity transactionEntity) {
    return TransactionBo.builder()
        .amount(transactionEntity.getAmount())
        .creditAccount(transactionEntity.getCreditAccountEntity().getId())
        .debitAccount(transactionEntity.getDebitAccountEntity().getId())
        .message(transactionEntity.getMessage())
        .id(transactionEntity.getId())
        .createdAt(transactionEntity.getCreatedAt())
        .build();
  }
}

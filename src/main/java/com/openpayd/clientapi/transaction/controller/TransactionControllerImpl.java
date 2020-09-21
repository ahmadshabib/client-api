package com.openpayd.clientapi.transaction.controller;

import com.openpayd.clientapi.common.model.Response;
import com.openpayd.clientapi.transaction.mapper.TransactionMapperImpl;
import com.openpayd.clientapi.transaction.model.Transaction;
import com.openpayd.clientapi.transaction.model.TransactionBo;
import com.openpayd.clientapi.transaction.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TransactionControllerImpl implements TransactionController {

  private final TransactionService transactionService;

  private final TransactionMapperImpl transactionMapper;

  @Autowired
  public TransactionControllerImpl(TransactionService transactionService, TransactionMapperImpl transactionMapper) {
    this.transactionService = transactionService;
    this.transactionMapper = transactionMapper;
  }

  @Override
  public ResponseEntity<Response> create(Transaction request) {
    log.info("Transaction {} is being processed", request);
    TransactionBo transactionToCreate = transactionMapper.convert(request);
    TransactionBo createdTransaction = transactionService.createTransaction(transactionToCreate);
    log.info("Transaction {} has been processed", createdTransaction);
    return Response.Success()
            .add("transaction", createdTransaction)
            .build();
  }

  @Override
  public ResponseEntity<Response> listAll(Long accountId) {
    log.info("Transaction list of account number {} is being retrieved", accountId);
    List<TransactionBo> transactionBos = transactionService.listAccountTransactions(accountId);
    List<Transaction> transactions = transactionBos.stream().map(transactionMapper::convert).collect(Collectors.toList());
    log.info("Transaction list of account number {} has been retrieved", accountId);
    return Response.Success()
            .add("transactions", transactions)
            .build();
  }
}

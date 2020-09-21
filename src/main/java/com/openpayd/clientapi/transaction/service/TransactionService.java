package com.openpayd.clientapi.transaction.service;

import com.openpayd.clientapi.transaction.model.TransactionBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface TransactionService {

  @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
  TransactionBo createTransaction(TransactionBo request);

  List<TransactionBo> listAccountTransactions(Long accountId);
}

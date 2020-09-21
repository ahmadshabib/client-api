package com.openpayd.clientapi.transaction.service;

import com.openpayd.clientapi.account.entity.BalanceStatus;
import com.openpayd.clientapi.account.mapper.AccountEntityMapperImpl;
import com.openpayd.clientapi.account.model.AccountBo;
import com.openpayd.clientapi.account.service.AccountService;
import com.openpayd.clientapi.transaction.entity.TransactionEntity;
import com.openpayd.clientapi.transaction.exception.InsufficientBalanceException;
import com.openpayd.clientapi.transaction.exception.InvalidBalanceStatusException;
import com.openpayd.clientapi.transaction.mapper.TransactionEntityMapperImpl;
import com.openpayd.clientapi.transaction.model.TransactionBo;
import com.openpayd.clientapi.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionServiceImpl implements TransactionService {

  private final AccountService accountService;

  private final TransactionEntityMapperImpl transactionEntityMapper;

  private final TransactionRepository transactionRepository;

  private final AccountEntityMapperImpl accountEntityMapper;

  @Autowired
  public TransactionServiceImpl(
      AccountService accountService,
      TransactionEntityMapperImpl transactionEntityMapper,
      TransactionRepository transactionRepository,
      AccountEntityMapperImpl accountEntityMapper) {
    this.accountService = accountService;
    this.transactionEntityMapper = transactionEntityMapper;
    this.transactionRepository = transactionRepository;
    this.accountEntityMapper = accountEntityMapper;
  }

  @Override
  public TransactionBo createTransaction(TransactionBo transactionBo) {
    AccountBo debitAccount = accountService.findAccountById(transactionBo.getDebitAccount());
    AccountBo creditAccount = accountService.findAccountById(transactionBo.getCreditAccount());
    validateDebitAccount(debitAccount, BalanceStatus.DR);
    validateDebitAccount(creditAccount, BalanceStatus.CR);
    validateBalance(creditAccount, transactionBo);
    TransactionEntity transaction = transactionEntityMapper.convert(transactionBo);
    transaction.setDebitAccountEntity(accountEntityMapper.convert(debitAccount));
    transaction.setCreditAccountEntity(accountEntityMapper.convert(creditAccount));
    TransactionEntity created = transactionRepository.save(transaction);
    BigDecimal newDebitBalance = debitAccount.getBalance().plus().add(transactionBo.getAmount());
    BigDecimal newCreditBalance = creditAccount.getBalance().subtract(transactionBo.getAmount());
    accountService.updateBalance(debitAccount.getClientId(), newDebitBalance);
    accountService.updateBalance(creditAccount.getClientId(), newCreditBalance);
    return transactionEntityMapper.convert(created);
  }

  @Override
  public List<TransactionBo> listAccountTransactions(Long accountId) {
    AccountBo account = accountService.findAccountById(accountId);
    return transactionRepository.findByAccountId(account.getClientId()).stream()
        .map(transactionEntityMapper::convert)
        .collect(Collectors.toList());
  }

  private void validateDebitAccount(AccountBo accountBo, BalanceStatus balanceStatus) {
    if (accountBo.getBalanceStatus() != balanceStatus)
      throw new InvalidBalanceStatusException("Wrong balance status");
  }

  private void validateBalance(AccountBo creditAccount, TransactionBo transactionBo) {
    if (creditAccount.getBalance().compareTo(transactionBo.getAmount()) < 0) {
      throw new InsufficientBalanceException();
    }
  }
}

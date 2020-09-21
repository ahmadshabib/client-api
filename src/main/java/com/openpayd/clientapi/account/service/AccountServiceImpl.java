package com.openpayd.clientapi.account.service;

import com.openpayd.clientapi.account.entity.AccountEntity;
import com.openpayd.clientapi.account.exception.AccountNotFoundException;
import com.openpayd.clientapi.account.mapper.AccountEntityMapperImpl;
import com.openpayd.clientapi.account.model.AccountBo;
import com.openpayd.clientapi.account.repository.AccountRepository;
import com.openpayd.clientapi.client.mapper.ClientEntityMapperImpl;
import com.openpayd.clientapi.client.model.ClientBo;
import com.openpayd.clientapi.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;
  private final ClientService clientService;
  private final ClientEntityMapperImpl clientEntityMapper;
  private final AccountEntityMapperImpl accountEntityMapper;

  @Autowired
  public AccountServiceImpl(AccountRepository accountRepository, ClientService clientService, ClientEntityMapperImpl clientEntityMapper, AccountEntityMapperImpl accountEntityMapper) {
    this.accountRepository = accountRepository;
    this.clientService = clientService;
    this.clientEntityMapper = clientEntityMapper;
    this.accountEntityMapper = accountEntityMapper;
  }

  @Override
  public AccountBo findAccountById(Long accountId) {
    return accountEntityMapper.convert(accountRepository.findById(accountId)
            .orElseThrow(AccountNotFoundException::new));
  }

  @Override
  public AccountBo createAccount(AccountBo accountBO) {
    ClientBo client = clientService.getClientById(accountBO.getClientId());
    AccountEntity account = accountEntityMapper.convert(accountBO);
    account.setClientEntity(clientEntityMapper.convert(client));
    AccountEntity accountEntity= accountRepository.save(account);
    return accountEntityMapper.convert(accountEntity);
  }

  @Override
  public void updateBalance(Long accountId, BigDecimal newBalance) {
    accountRepository.updateBalance(accountId, newBalance);
  }
}

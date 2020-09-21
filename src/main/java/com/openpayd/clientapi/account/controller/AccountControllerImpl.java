package com.openpayd.clientapi.account.controller;

import com.openpayd.clientapi.account.mapper.AccountMapperImpl;
import com.openpayd.clientapi.account.model.Account;
import com.openpayd.clientapi.account.model.AccountBo;
import com.openpayd.clientapi.account.service.AccountService;
import com.openpayd.clientapi.common.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Qualifier
public class AccountControllerImpl implements AccountController {

  private final AccountService accountService;
  private final AccountMapperImpl accountMapper;

  @Autowired
  public AccountControllerImpl(AccountService accountService, AccountMapperImpl accountMapper) {
    this.accountService = accountService;
    this.accountMapper = accountMapper;
  }

  @Override
  public ResponseEntity<Response> getById(Long accountId) {
    AccountBo accountBo = accountService.findAccountById(accountId);
    Account account = accountMapper.convert(accountBo);
    return Response.Success()
            .add("account", account)
            .build();
  }

  @Override
  public ResponseEntity<Response> create(Account request) {
    AccountBo accountToBeCreated = accountMapper.convert(request);
    AccountBo createdAccount = accountService.createAccount(accountToBeCreated);
    Account account = accountMapper.convert(createdAccount);
    return Response.Success()
            .add("accountId", account.getClientId())
            .build();
  }
}

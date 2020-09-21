package com.openpayd.clientapi.account.mapper;

import com.openpayd.clientapi.account.model.Account;
import com.openpayd.clientapi.account.model.AccountBo;
import com.openpayd.clientapi.common.mapper.AbstractBoMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl extends AbstractBoMapper<Account, AccountBo> {

  @Override
  public Account convert(AccountBo accountBo) {
    return new Account(accountBo.getClientId(), accountBo.getAccountType(), accountBo.getBalance(), accountBo.getBalanceStatus());
  }

  @Override
  public AccountBo convert(Account account) {
    return AccountBo.builder()
            .clientId(account.getClientId())
            .balance(account.getBalance())
            .balanceStatus(account.getBalanceStatus())
            .accountType(account.getAccountType())
            .build();
  }
}

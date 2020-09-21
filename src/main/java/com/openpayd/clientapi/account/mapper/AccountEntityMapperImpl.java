package com.openpayd.clientapi.account.mapper;

import com.openpayd.clientapi.account.entity.AccountEntity;
import com.openpayd.clientapi.account.model.AccountBo;
import com.openpayd.clientapi.common.mapper.AbstractBoMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountEntityMapperImpl extends AbstractBoMapper<AccountEntity, AccountBo> {
  @Override
  public AccountEntity convert(AccountBo accountBo) {
    AccountEntity accountEntity = new AccountEntity();
    accountEntity.setId(accountBo.getClientId());
    accountEntity.setBalance(accountBo.getBalance());
    accountEntity.setStatus(accountBo.getBalanceStatus());
    accountEntity.setType(accountBo.getAccountType());
    return accountEntity;
  }

  @Override
  public AccountBo convert(AccountEntity accountEntity) {
    return AccountBo.builder()
        .clientId(accountEntity.getId())
        .balance(accountEntity.getBalance())
        .balanceStatus(accountEntity.getStatus())
        .accountType(accountEntity.getType())
        .build();
  }
}

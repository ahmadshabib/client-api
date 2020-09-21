package com.openpayd.clientapi.account.service;


import com.openpayd.clientapi.account.model.AccountBo;

import java.math.BigDecimal;

public interface AccountService {

  AccountBo findAccountById(Long accountId);

  AccountBo createAccount(AccountBo accountBO);

   void updateBalance(Long accountId, BigDecimal newBalance);
}

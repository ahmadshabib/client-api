package com.openpayd.clientapi.account.repository;


import com.openpayd.clientapi.account.entity.AccountEntity;
import com.openpayd.clientapi.account.repository.AccountRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@DataJpaTest
@Transactional
class AccountRepositoryTest {

  @Autowired
  private AccountRepository accountRepository;

  @Test
  @Sql("/queries/account.sql")
  void updateBalanceTest() {
    accountRepository.updateBalance(1L, BigDecimal.valueOf(999));
    AccountEntity result = accountRepository.findById(1L).get();
    assertEquals(BigDecimal.valueOf(999).intValue(), result.getBalance().intValue());
  }

}

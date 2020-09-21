package com.openpayd.clientapi.transaction.repository;

import com.openpayd.clientapi.account.entity.AccountEntity;
import com.openpayd.clientapi.transaction.entity.TransactionEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@Transactional
class TransactionRepositoryTest {

  @Autowired private TransactionRepository transactionRepository;

  @Test
  @Sql("/queries/multi_accounts_preparation.sql")
  void updateBalanceTest() {
    // Set up the credit Account
    AccountEntity creditAccount = new AccountEntity();
    creditAccount.setId(1L);

    // set up the debit account
    AccountEntity debitAccount = new AccountEntity();
    debitAccount.setId(2L);

    // Set up the transaction
    TransactionEntity transactionEntity = new TransactionEntity();
    transactionEntity.setCreditAccountEntity(creditAccount);
    transactionEntity.setDebitAccountEntity(debitAccount);
    transactionEntity.setAmount(BigDecimal.ONE);

    // save the transaction
    transactionRepository.save(transactionEntity);

    List<TransactionEntity> result = transactionRepository.findByAccountId(1L);
    assertEquals(result.size(), 1);
  }
}

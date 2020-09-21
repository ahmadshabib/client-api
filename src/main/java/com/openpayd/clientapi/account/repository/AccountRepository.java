package com.openpayd.clientapi.account.repository;

import com.openpayd.clientapi.account.entity.AccountEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {

  @Modifying
  @Query("UPDATE AccountEntity a SET a.balance = :newBalance WHERE a.id = :accountId")
  void updateBalance(@Param("accountId") Long accountId, @Param("newBalance") BigDecimal newBalance);

}

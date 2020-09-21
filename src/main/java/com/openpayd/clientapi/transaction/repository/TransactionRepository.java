package com.openpayd.clientapi.transaction.repository;

import com.openpayd.clientapi.transaction.entity.TransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {

  @Query("SELECT t FROM TransactionEntity t WHERE t.debitAccountEntity.id = :accountId OR t.creditAccountEntity.id = :accountId")
  List<TransactionEntity> findByAccountId(@Param("accountId") Long accountId);
}

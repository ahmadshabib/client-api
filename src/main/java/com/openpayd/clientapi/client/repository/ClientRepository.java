package com.openpayd.clientapi.client.repository;

import com.openpayd.clientapi.client.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {}

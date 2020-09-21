package com.openpayd.clientapi.client.mapper;

import com.openpayd.clientapi.client.entity.ClientEntity;
import com.openpayd.clientapi.client.model.ClientBo;
import com.openpayd.clientapi.common.mapper.AbstractBoMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientEntityMapperImpl extends AbstractBoMapper<ClientEntity, ClientBo> {

  public ClientEntity convert(ClientBo clientBo) {
    ClientEntity clientEntity = new ClientEntity();
    clientEntity.setName(clientBo.getName());
    clientEntity.setSurname(clientBo.getSurname());
    clientEntity.setPrimaryAddress(clientBo.getPrimaryAddress());
    clientEntity.setSecondaryAddress(clientBo.getSecondaryAddress());
    clientEntity.setAccountEntities(clientBo.getAccountEntities());
    return clientEntity;
  }

  public ClientBo convert(ClientEntity clientEntity) {
    return ClientBo.builder()
            .name(clientEntity.getName())
            .surname(clientEntity.getSurname())
            .primaryAddress(clientEntity.getPrimaryAddress())
            .secondaryAddress(clientEntity.getSecondaryAddress())
            .accountEntities(clientEntity.getAccountEntities())
            .build();
  }
}


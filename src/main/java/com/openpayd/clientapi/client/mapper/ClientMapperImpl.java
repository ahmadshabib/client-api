package com.openpayd.clientapi.client.mapper;

import com.openpayd.clientapi.client.model.Client;
import com.openpayd.clientapi.client.model.ClientBo;
import com.openpayd.clientapi.common.mapper.AbstractBoMapper;
import org.springframework.stereotype.Component;

// Note: Change this
@Component
public class ClientMapperImpl extends AbstractBoMapper<Client, ClientBo> {

  public Client convert(ClientBo clientBo) {
    return new Client(clientBo.getName(), clientBo.getSurname(),clientBo.getPrimaryAddress(),clientBo.getSecondaryAddress());
  }

  public ClientBo convert(Client client) {
    return ClientBo.builder()
            .name(client.getName())
            .surname(client.getSurname())
            .primaryAddress(client.getPrimaryAddress())
            .secondaryAddress(client.getSecondaryAddress())
            .build();
  }
}

package com.openpayd.clientapi.client.controller;

import com.openpayd.clientapi.account.entity.AccountEntity;
import com.openpayd.clientapi.client.mapper.ClientMapperImpl;
import com.openpayd.clientapi.client.model.Client;
import com.openpayd.clientapi.client.model.ClientBo;
import com.openpayd.clientapi.client.service.ClientService;
import com.openpayd.clientapi.common.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientControllerImpl implements ClientController {

  private final ClientService clientService;

  private final ClientMapperImpl clientMapper;

  @Autowired
  public ClientControllerImpl(ClientService clientService, ClientMapperImpl clientMapper) {
    this.clientService = clientService;
    this.clientMapper = clientMapper;
  }

  @Override
  public ResponseEntity<Response> getById(Long clientId) {
    Client client = clientMapper.convert(clientService.getClientById(clientId));
    return Response.success().add("client", client).build();
  }

  @Override
  public ResponseEntity<Response> create(Client clientRequest) {
    ClientBo clientBo = clientService.createClient(clientMapper.convert(clientRequest));
    Client client = clientMapper.convert(clientBo);
    return Response.success().add("client", client).build();
  }

  @Override
  public ResponseEntity<Response> listClients() {
    List<ClientBo> clients = clientService.listAll();
    List<Client> response =
        clients.stream().map(clientMapper::convert).collect(Collectors.toList());

    return Response.success().add("clients", response).build();
  }

  @Override
  public ResponseEntity<Response> listAccounts(Long clientId) {
    ClientBo client = clientService.getClientById(clientId);
    List<AccountEntity> accountEntities = client.getAccountEntities();
    return Response.success().add("accountEntities", accountEntities).build();
  }
}

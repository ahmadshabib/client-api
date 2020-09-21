package com.openpayd.clientapi.client.service;

import com.openpayd.clientapi.client.model.ClientBo;

import java.util.List;

public interface ClientService {

  ClientBo getClientById(Long clientId);

  ClientBo createClient(ClientBo request);

  List<ClientBo> listAll();
}

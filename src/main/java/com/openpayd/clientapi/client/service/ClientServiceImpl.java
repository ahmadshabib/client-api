package com.openpayd.clientapi.client.service;

import com.openpayd.clientapi.client.entity.ClientEntity;
import com.openpayd.clientapi.client.exception.ClientNotFoundException;
import com.openpayd.clientapi.client.mapper.ClientEntityMapperImpl;
import com.openpayd.clientapi.client.model.ClientBo;
import com.openpayd.clientapi.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;

  private final ClientEntityMapperImpl clientEntityMapper;

  @Autowired
  public ClientServiceImpl(
      ClientRepository clientRepository, ClientEntityMapperImpl clientEntityMapper) {
    this.clientRepository = clientRepository;
    this.clientEntityMapper = clientEntityMapper;
  }

  @Override
  public ClientBo getClientById(Long clientId) {
    ClientEntity clientEntity =
        clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
    return clientEntityMapper.convert(clientEntity);
  }

  @Override
  public ClientBo createClient(ClientBo clientBo) {
    ClientEntity client = clientEntityMapper.convert(clientBo);
    clientRepository.save(client);
    return clientEntityMapper.convert(client);
  }

  @Override
  public List<ClientBo> listAll() {
    List<ClientBo> result = new ArrayList<>();
    clientRepository.findAll().forEach(client -> result.add(clientEntityMapper.convert(client)));
    return result;
  }
}

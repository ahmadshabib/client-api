package com.openpayd.clientapi.client.controller;


import com.openpayd.clientapi.client.model.Client;
import com.openpayd.clientapi.common.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public interface ClientController {

  @GetMapping("/client/{clientId}")
  ResponseEntity<Response> getById(@PathVariable Long clientId);


  @PostMapping("/client")
  ResponseEntity<Response> create(@Validated @RequestBody Client client);


  @GetMapping("/clients")
  ResponseEntity<Response> listClients();

  @GetMapping("/client/{clientId}/accounts")
  ResponseEntity<Response> listAccounts(@PathVariable Long clientId);

}

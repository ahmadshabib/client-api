package com.openpayd.clientapi.transaction.controller;


import com.openpayd.clientapi.common.model.Response;
import com.openpayd.clientapi.transaction.model.Transaction;
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
public interface TransactionController {

  @PostMapping("/transfer")
  ResponseEntity<Response> create(@Validated @RequestBody Transaction request);

  @GetMapping("/transfers/{accountId}")
  ResponseEntity<Response> listAll(@PathVariable Long accountId);
}
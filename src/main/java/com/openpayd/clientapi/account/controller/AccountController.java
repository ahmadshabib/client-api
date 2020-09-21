package com.openpayd.clientapi.account.controller;

import com.openpayd.clientapi.account.model.Account;
import com.openpayd.clientapi.account.model.AccountBo;
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
public interface AccountController {


  @GetMapping("/accountEntities/{accountId}")
  ResponseEntity<Response> getById(@PathVariable Long accountId);

  @PostMapping("/account")
  ResponseEntity<Response> create(@Validated @RequestBody Account account);

}

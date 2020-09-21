package com.openpayd.clientapi.account.exception;

public class AccountNotFoundException extends RuntimeException {
  public AccountNotFoundException() {
    super("Account was not found");
  }
}

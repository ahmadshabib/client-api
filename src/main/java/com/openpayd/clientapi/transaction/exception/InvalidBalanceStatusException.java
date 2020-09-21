package com.openpayd.clientapi.transaction.exception;

public class InvalidBalanceStatusException extends  RuntimeException {
  public  InvalidBalanceStatusException(String message){
    super(message);
  }
}

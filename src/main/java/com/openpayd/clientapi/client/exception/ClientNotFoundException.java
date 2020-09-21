package com.openpayd.clientapi.client.exception;

public class ClientNotFoundException extends RuntimeException {
  public ClientNotFoundException() {
    super("Client Was not Found ");
  }
}

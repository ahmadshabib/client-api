package com.openpayd.clientapi.common;


import com.openpayd.clientapi.account.exception.AccountNotFoundException;
import com.openpayd.clientapi.client.exception.ClientNotFoundException;
import com.openpayd.clientapi.common.model.Response;
import com.openpayd.clientapi.transaction.exception.InsufficientBalanceException;
import com.openpayd.clientapi.transaction.exception.InvalidBalanceStatusException;
import lombok.extern.slf4j.Slf4j;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler({
          ClientNotFoundException.class,
          AccountNotFoundException.class,
          InvalidBalanceStatusException.class,
          InsufficientBalanceException.class
  })
  public ResponseEntity<Response> handleApplicationException(Exception exception) {
    log.error("Application exception while processing request ", exception);
    return Response.Error(BAD_REQUEST).message(exception.getMessage()).build();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Response> handleOthers(Exception exception) {
    log.error("Exception while processing request ", exception);
    return Response.Error(INTERNAL_SERVER_ERROR).message("Something went wrong").build();
  }
}

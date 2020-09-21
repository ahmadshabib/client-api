package com.openpayd.clientapi.account.service;

import com.openpayd.clientapi.account.entity.AccountEntity;
import com.openpayd.clientapi.account.entity.AccountType;
import com.openpayd.clientapi.account.entity.BalanceStatus;
import com.openpayd.clientapi.account.exception.AccountNotFoundException;
import com.openpayd.clientapi.account.mapper.AccountEntityMapperImpl;
import com.openpayd.clientapi.account.model.AccountBo;
import com.openpayd.clientapi.account.repository.AccountRepository;
import com.openpayd.clientapi.client.entity.ClientEntity;
import com.openpayd.clientapi.client.exception.ClientNotFoundException;
import com.openpayd.clientapi.client.mapper.ClientEntityMapperImpl;
import com.openpayd.clientapi.client.model.ClientBo;
import com.openpayd.clientapi.client.service.ClientService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;


class AccountServiceTest {

  @Mock
  private AccountRepository accountRepo;

  @Mock
  private ClientService clientService;

  @Mock
  private ClientEntityMapperImpl clientEntityMapper;

  @Mock
  private AccountEntityMapperImpl accountEntityMapper;

  @InjectMocks
  private AccountServiceImpl accountService;


  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void retrieveAccountByIDTest() {
    AccountEntity account = new AccountEntity();

    account.setId(1L);
    account.setType(AccountType.CURRENT);
    account.setStatus(BalanceStatus.DR);
    account.setBalance(BigDecimal.valueOf(100));

    AccountBo accountBo = AccountBo.builder()
            .clientId(1L)
            .accountType(AccountType.CURRENT)
            .balanceStatus(BalanceStatus.DR)
            .balance(BigDecimal.valueOf(100))
            .build();

    when(accountRepo.findById(1L)).thenReturn(Optional.of(account));
    when(accountEntityMapper.convert(any(AccountEntity.class))).thenReturn(accountBo);

    AccountBo result = accountService.findAccountById(1L);

    assertEquals(1L, result.getClientId());
    assertEquals(BalanceStatus.DR, result.getBalanceStatus());
    assertEquals(BigDecimal.valueOf(100), result.getBalance());
  }

  @Test
  void accountCreationTest() {
    AccountBo request = AccountBo.builder()
            .clientId(999L)
            .balanceStatus(BalanceStatus.CR)
            .balance(BigDecimal.valueOf(100))
            .accountType(AccountType.SAVINGS)
            .build();

    AccountEntity expected = new AccountEntity();
    expected.setStatus(request.getBalanceStatus());
    expected.setBalance(request.getBalance());
    expected.setType(request.getAccountType());
    expected.setId(1L);

    when(accountRepo.save(any(AccountEntity.class))).thenReturn(expected);
    ClientBo clientBo = ClientBo.builder().build();
    when(clientService.getClientById(999L)).thenReturn(clientBo);
    when(clientEntityMapper.convert(any(ClientEntity.class))).thenReturn(clientBo);
    when(clientEntityMapper.convert(any(ClientBo.class))).thenReturn(new ClientEntity());
    when(accountEntityMapper.convert(any(AccountBo.class))).thenReturn(expected);
    when(accountEntityMapper.convert(any(AccountEntity.class))).thenReturn(request);

    AccountBo result = accountService.createAccount(request);

    assertEquals(999L, result.getClientId());
    assertEquals(BigDecimal.valueOf(100), result.getBalance());
    assertEquals(AccountType.SAVINGS, result.getAccountType());
    assertEquals(BalanceStatus.CR, result.getBalanceStatus());
  }

  @Test
  void clientNotFoundExceptionTest() {
    AccountBo request = AccountBo.builder()
            .clientId(999L)
            .build();
    when(clientService.getClientById(999L)).thenThrow(new ClientNotFoundException());
    assertThrows(ClientNotFoundException.class, () -> accountService.createAccount(request));
  }

  @Test
  void accountNotFoundExceptionTest() {
    when(accountRepo.findById(1L)).thenReturn(Optional.empty());
    assertThrows(AccountNotFoundException.class, () -> accountService.findAccountById(1L));
  }

}

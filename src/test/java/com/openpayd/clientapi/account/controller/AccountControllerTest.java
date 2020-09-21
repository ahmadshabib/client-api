package com.openpayd.clientapi.account.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.fasterxml.jackson.databind.node.JsonNodeType.NUMBER;
import com.openpayd.clientapi.account.entity.AccountEntity;
import com.openpayd.clientapi.account.entity.AccountType;
import com.openpayd.clientapi.account.entity.BalanceStatus;
import com.openpayd.clientapi.account.mapper.AccountEntityMapperImpl;
import com.openpayd.clientapi.account.mapper.AccountMapperImpl;
import com.openpayd.clientapi.account.model.Account;
import com.openpayd.clientapi.account.model.AccountBo;
import com.openpayd.clientapi.account.service.AccountService;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

@WebMvcTest(controllers = AccountController.class)
@AutoConfigureRestDocs
class AccountControllerTest {

  @MockBean private AccountService accountService;

  @Autowired private MockMvc mockMvc;

  @MockBean AccountMapperImpl accountMapper;

  @MockBean AccountEntityMapperImpl accountEntityMapper;

  private static ResultMatcher jsonResult = content().contentType(MediaType.APPLICATION_JSON);

  static String asJson(Object object) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(object);
  }

  @Test
  void retrieveById() throws Exception {
    Account account =
        Account.builder()
            .balance(BigDecimal.TEN)
            .accountType(AccountType.CURRENT)
            .clientId(1L)
            .balanceStatus(BalanceStatus.DR)
            .build();

    AccountBo accountBo =
        AccountBo.builder()
            .balance(BigDecimal.TEN)
            .accountType(AccountType.CURRENT)
            .clientId(1L)
            .balanceStatus(BalanceStatus.DR)
            .build();

    when(accountService.findAccountById(1L)).thenReturn(accountBo);
    when(accountEntityMapper.convert(any(AccountEntity.class))).thenReturn(accountBo);
    when(accountMapper.convert(any(AccountBo.class))).thenReturn(account);

    mockMvc
        .perform(
            get("/api/accounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonResult)
        .andExpect(jsonPath("$.fields.account.clientId").value(1L))
        .andExpect(jsonPath("$.fields.account.balance").value(10))
        .andExpect(jsonPath("$.fields.account.balanceStatus").value("DR"))
        .andExpect(jsonPath("$.fields.account.accountType").value("CURRENT"))
        .andDo(
            document(
                "account/list_account",
                responseFields(
                    fieldWithPath("fields.account.clientId").description("Id of the associated client"),
                    fieldWithPath("fields.account.accountType")
                        .description("fieldsAccount type, can be ['SAVINGS', 'CURRENT']"),
                    fieldWithPath("fields.account.balance").description("Current"),
                    fieldWithPath("fields.account.balanceStatus")
                        .description("Balance status, can be ['DR', 'CR']"))));
  }

  @Test
  void accountCreation() throws Exception {
    Account request =
        Account.builder()
            .accountType(AccountType.SAVINGS)
            .balance(BigDecimal.TEN)
            .balanceStatus(BalanceStatus.CR)
            .clientId(999L)
            .build();

    AccountBo account =
        AccountBo.builder()
            .clientId(999L)
            .accountType(request.getAccountType())
            .balanceStatus(request.getBalanceStatus())
            .balance(request.getBalance())
            .build();

    when(accountService.createAccount(any(AccountBo.class))).thenReturn(account);
    when(accountMapper.convert(any(Account.class))).thenReturn(account);
    when(accountMapper.convert(any(AccountBo.class))).thenReturn(request);

    mockMvc
        .perform(
            post("/api/account")
                .content(asJson(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonResult)
        .andExpect(jsonPath("$.fields.accountId").value(999L))
        .andDo(
            document(
                "account/create_account",
                requestFields(
                    fieldWithPath("clientId").description(""),
                    fieldWithPath("accountType")
                        .description("Account type, can be ['SAVINGS', 'CURRENT']"),
                    fieldWithPath("balance").description("Current balance"),
                    fieldWithPath("balanceStatus")
                        .description("Balance status, can be ['DR', 'CR']")),
                responseFields(
                    fieldWithPath("fields.accountId")
                        .type(NUMBER)
                        .description("Id of the account"))));
  }
}

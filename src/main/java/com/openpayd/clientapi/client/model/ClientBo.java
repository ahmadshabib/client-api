package com.openpayd.clientapi.client.model;

import com.openpayd.clientapi.account.entity.AccountEntity;
import com.openpayd.clientapi.common.model.AbstractBo;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class ClientBo extends AbstractBo {

  @NotBlank
  private String name;

  @NotBlank
  private String surname;

  @NotBlank
  private String primaryAddress;

  private String secondaryAddress;

  private List<AccountEntity> accountEntities;
}

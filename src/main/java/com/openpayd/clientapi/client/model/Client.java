package com.openpayd.clientapi.client.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
@Builder
public class Client {


  @JsonCreator
  public Client(@JsonProperty("name") String name, @JsonProperty("surname") String surname, @JsonProperty("primaryAddress") String primaryAddress, @JsonProperty("secondaryAddress") String secondaryAddress) {
    this.name = name;
    this.primaryAddress = primaryAddress;
    this.surname = surname;
    this.secondaryAddress = secondaryAddress;
  }

  @NotBlank
  private String name;

  @NotBlank
  private String surname;

  @NotBlank
  private String primaryAddress;

  private String secondaryAddress;
}
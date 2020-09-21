package com.openpayd.clientapi.client.entity;

import com.openpayd.clientapi.account.entity.AccountEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Table
@Entity
@Data
public class ClientEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String surname;

  @Column(length = 256, nullable = false)
  private String primaryAddress;

  @Column(length = 256)
  private String secondaryAddress;

  @OneToMany(mappedBy = "clientEntity")
  private List<AccountEntity> accountEntities;
}
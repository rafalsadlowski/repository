package com.empik.repository.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_LOG")
public class UserLogEntity {

  public UserLogEntity() {
    this("DAFAULT");
  }

  public UserLogEntity(String login) {
    setLogin(login);
    setRequestCount(0);
  }

  @Id
  private String login;

  @Column(name = "REQUEST_COUNT")
  private Integer requestCount;
}

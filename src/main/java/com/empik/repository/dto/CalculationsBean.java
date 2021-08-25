package com.empik.repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CalculationsBean { 

  @JsonProperty("id")
  public Integer id; 

  @JsonProperty("login")
  public String login;
  

  @JsonProperty("name")
  public String name;
 

  @JsonProperty("type") 
  public String type;
  

  @JsonProperty("avatar_url") 
  public String avatarUrl;
  

  @JsonProperty("created_at")
  public String createdAt;
  

  public Float calculations;
   
}

package com.empik.repository.dto;
 
import lombok.Data;

@Data
public class CalculationsBean { 
 
  public Integer id; 
 
  public String login;
 
  public String name;
  
  public String type;
   
  public String avatarUrl;
  
  public String createdAt;
  
  public Float calculations;
   
}

package com.example.demo_mv.dto;

import com.example.demo_mv.users.UserEntity;

public class ReturnTypeDto {
  
  public Boolean status;
  public UserEntity data = null;

  public ReturnTypeDto(UserEntity data){
    this.data = data;
  }

  public ReturnTypeDto(Boolean status, UserEntity data){
    this.status = status;
    this.data = data;
  }
}

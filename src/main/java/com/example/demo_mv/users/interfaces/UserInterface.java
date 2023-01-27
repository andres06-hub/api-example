package com.example.demo_mv.users.interfaces;

import java.util.List;

import com.example.demo_mv.dto.ReturnTypeDto;
import com.example.demo_mv.users.UserEntity;

public interface UserInterface {
  
  public List<UserEntity> listUsers();
  public UserEntity findById(Long id);
  public UserEntity findByMail(String mail);
  public ReturnTypeDto saveUser(UserEntity user);
  public ReturnTypeDto deleteUser(Long id);
}

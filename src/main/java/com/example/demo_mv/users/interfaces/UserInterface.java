package com.example.demo_mv.users.interfaces;

import java.util.List;

import com.example.demo_mv.users.UserEntity;

public interface UserInterface {
  
  public List<UserEntity> listUsers();
  public UserEntity findById(Long id);
  public UserEntity saveUser(UserEntity user);
  public void delete(UserEntity user);
}

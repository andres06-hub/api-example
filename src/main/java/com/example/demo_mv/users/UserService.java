package com.example.demo_mv.users;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo_mv.users.interfaces.UserInterface;

import jakarta.annotation.Resource;

@Service
public class UserService implements UserInterface {
  
  @Resource
  UserRepository _userRepository;

  @Override
  public List<UserEntity> listUsers() {
    return (List<UserEntity>) _userRepository.findAll();
  }

  @Override
  public UserEntity findById(Long id) {
    return _userRepository.findById(id).orElse(null);
  }

  @Override
  public UserEntity saveUser(UserEntity user) {
    return _userRepository.save(user);
  }

  @Override
  public void delete(UserEntity user) {
    try {
      _userRepository.delete(user);
    } catch (Exception e) {
      System.out.println("ERROR:: " + e);
      // return false;
    }
    // return true; 
  }

}

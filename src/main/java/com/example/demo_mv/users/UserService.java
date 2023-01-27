package com.example.demo_mv.users;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo_mv.common.services.BcryptService;
import com.example.demo_mv.users.dto.ReturnTypeDto;
import com.example.demo_mv.users.interfaces.UserInterface;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements UserInterface {
  
  private BcryptService bct;

  public UserService(){
    bct = new BcryptService();
  }

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
  public UserEntity findByMail(String mail) {
    return _userRepository.findByMail(mail);
  }

  @Transactional
  @Override
  public ReturnTypeDto saveUser(UserEntity user) {
    log.info("Looking for user...");
    UserEntity foundUser = this.findByMail(user.email);
    if (foundUser != null ) return new ReturnTypeDto(true, foundUser);
    log.info("Saving user...");
    String pwdHash = this.bct.BcryptData(user.password);
    user.setPassword(pwdHash);
    UserEntity result = _userRepository.save(user);
    return new ReturnTypeDto(false, result);
  }

  @Transactional
  @Override
  public ReturnTypeDto deleteUser(Long id) {
    UserEntity foundUser = this.findById(id);
    if (foundUser == null ) return new ReturnTypeDto(false);
    log.info("DELETING USER...");
    _userRepository.deleteById(id);
    log.info("DELETED USER.");
    return new ReturnTypeDto(true);
  }

}

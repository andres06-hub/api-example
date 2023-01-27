package com.example.demo_mv.users;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_mv.dto.ResponseDto;
import com.example.demo_mv.dto.ReturnTypeDto;

import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
  
  @Resource
  UserService service;

  @GetMapping("/users")
  public ResponseEntity<ResponseDto> getAll() {
    List<UserEntity> users = service.listUsers();
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(true, "USERS: ", users));
  }

  @PostMapping("/user")
  public ResponseEntity<ResponseDto> createUser(@RequestBody UserEntity user) {
    ReturnTypeDto result = service.saveUser(user);
    if (result.status == true) return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(true, "Exist User!", result.data));
    log.info("User created: "+result.data);
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(true, "Created User!", null));
  }
}

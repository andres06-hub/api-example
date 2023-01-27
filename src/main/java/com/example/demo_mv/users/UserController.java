package com.example.demo_mv.users;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_mv.users.dto.ResponseDto;
import com.example.demo_mv.users.dto.ReturnTypeDto;

import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    List<UserEntity> users = this.service.listUsers();
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(true, "USERS: ", users));
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<ResponseDto> getUser(@PathVariable("id") String id){
    //TODO: CREATE FACTORY FOR ID, String -> Long
    Long _id = (long) Integer.parseInt(id);
    UserEntity result = this.service.findById(_id);
    if (result == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(true,"User not found",result));
    log.info("USER FOUND!");
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(true, "user", result));
  }

  @PostMapping("/user")
  public ResponseEntity<ResponseDto> createUser(@RequestBody UserEntity user) {
    ReturnTypeDto result = this.service.saveUser(user);
    if (result.status == true) return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(true, "Exist User!", result.data));
    log.info("User created: "+result.data);
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(true, "Created User!", null));
  }

  @DeleteMapping("/user/{id}")
  public ResponseEntity<ResponseDto> delUser(@PathVariable("id") String id) {
    //TODO: CREATE FACTORY FOR ID, String -> Long
    Long _id = (long) Integer.parseInt(id);
    ReturnTypeDto result = this.service.deleteUser(_id);
    if (result.status == false) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(true, "User does not exist!", null));
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(true,"Deleted User!",null));
  }
}

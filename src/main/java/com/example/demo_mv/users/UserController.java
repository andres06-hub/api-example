package com.example.demo_mv.users;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_mv.dto.ResponseDto;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UserController {
  
  @Resource
  UserService service;

  @GetMapping()
  public ResponseEntity<ResponseDto> getAll() {
    List<UserEntity> users = service.listUsers();
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(true, "USERS: ", users));
  }

  @PostMapping()
  public ResponseEntity<ResponseDto> createUser(@RequestBody UserEntity user) {
    System.out.println("DATA: " + user);
    service.saveUser(user);
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(true, "Created User!", null));
  }

  // @PostMapping()
  // public ResponseEntity<ResponseDto> delUser(@RequestParam String id) {
  //   // service.delete();
  //   return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(true,"Deleted User!",null));
  // }
  
}

package com.example.demo_mv.users;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
  @Query("SELECT u FROM UserEntity u WHERE u.email = ?1 ")
  public UserEntity findByMail(String mail);
}

package com.myproject.boardback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.boardback.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
  
  // sql문 where절 자동생성(이메일에 대한 값을 찾고 존재하면 true반환)
  boolean existsByEmail(String email);
  boolean existsByNickname(String nickname);
  boolean existsByTelNumber(String telNumber);
  UserEntity findByEmail(String email);

}

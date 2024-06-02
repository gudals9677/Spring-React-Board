package com.myproject.boardback.service;

import org.springframework.http.ResponseEntity;

import com.myproject.boardback.dto.response.user.GetSignInUserResponseDTO;

public interface UserService {
  
  ResponseEntity<? super GetSignInUserResponseDTO> getSignInUser(String email);
}

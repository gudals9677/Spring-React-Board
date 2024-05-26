package com.myproject.boardback.service;

import org.springframework.http.ResponseEntity;

import com.myproject.boardback.dto.request.auth.SignInRequestDTO;
import com.myproject.boardback.dto.request.auth.SignUpRequestDTO;
import com.myproject.boardback.dto.response.auth.SignInResponseDTO;
import com.myproject.boardback.dto.response.auth.SignUpResponseDTO;

public interface AuthService {

  ResponseEntity<? super SignUpResponseDTO> signUp(SignUpRequestDTO dto);
  ResponseEntity<? super SignInResponseDTO> signIn(SignInRequestDTO dto);
} 
  


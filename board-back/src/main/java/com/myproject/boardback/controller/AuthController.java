package com.myproject.boardback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.boardback.dto.request.auth.SignInRequestDTO;
import com.myproject.boardback.dto.request.auth.SignUpRequestDTO;
import com.myproject.boardback.dto.response.auth.SignInResponseDTO;
import com.myproject.boardback.dto.response.auth.SignUpResponseDTO;
import com.myproject.boardback.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  
  private final AuthService authService;

  @PostMapping("/sign-up")
  public ResponseEntity<? super SignUpResponseDTO> signUp(
    @RequestBody @Valid SignUpRequestDTO requestBody
    ) {
    ResponseEntity<? super SignUpResponseDTO> response = authService.signUp(requestBody);
    return response;
  }

  @PostMapping("/sign-in")
  public ResponseEntity<? super SignInResponseDTO> signIn(
    @RequestBody @Valid SignInRequestDTO requestBody
  ){
    ResponseEntity<? super SignInResponseDTO> response = authService.signIn(requestBody);
    return response;
  }
  
}

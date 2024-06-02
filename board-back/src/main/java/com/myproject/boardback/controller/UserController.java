package com.myproject.boardback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.boardback.dto.response.user.GetSignInUserResponseDTO;
import com.myproject.boardback.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
  
  private final UserService userService;

  @GetMapping("")
  public ResponseEntity<? super GetSignInUserResponseDTO> getSignInUser(@AuthenticationPrincipal String email) {
    ResponseEntity<? super GetSignInUserResponseDTO> response = userService.getSignInUser(email);
    return response;
  }

}

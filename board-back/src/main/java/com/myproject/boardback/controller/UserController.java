package com.myproject.boardback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.boardback.dto.request.user.PatchNicknameRequestDTO;
import com.myproject.boardback.dto.request.user.PatchProfileImageRequestDTO;
import com.myproject.boardback.dto.response.user.GetSignInUserResponseDTO;
import com.myproject.boardback.dto.response.user.GetUserResponseDTO;
import com.myproject.boardback.dto.response.user.PatchNicknameResponseDTO;
import com.myproject.boardback.dto.response.user.PatchProfileImageResponseDTO;
import com.myproject.boardback.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
  
  private final UserService userService;

  // 유저 조회
  @GetMapping("/{email}")
  public ResponseEntity<? super GetUserResponseDTO> getUser(
    @PathVariable("email") String email
  ){
    ResponseEntity<? super GetUserResponseDTO> response = userService.getUser(email);
    return response;
  }

  // 로그인 
  @GetMapping("")
  public ResponseEntity<? super GetSignInUserResponseDTO> getSignInUser(@AuthenticationPrincipal String email) {
    ResponseEntity<? super GetSignInUserResponseDTO> response = userService.getSignInUser(email);
    return response;
  }

  @PatchMapping("/nickname")
  public ResponseEntity<? super PatchNicknameResponseDTO> patchNickname(
    @RequestBody @Valid PatchNicknameRequestDTO requestBody,
    @AuthenticationPrincipal String email
  ){
    ResponseEntity<? super PatchNicknameResponseDTO> response = userService.patchNickname(requestBody, email);
    return response;
  }
  @PatchMapping("/profile-image")
  public ResponseEntity<? super PatchProfileImageResponseDTO> patchProfileImage(
    @RequestBody @Valid PatchProfileImageRequestDTO requestBody,
    @AuthenticationPrincipal String email
  ){
    ResponseEntity<? super PatchProfileImageResponseDTO> response = userService.patchProfileImage(requestBody, email);
    return response;
  }
}

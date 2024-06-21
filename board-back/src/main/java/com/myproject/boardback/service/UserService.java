package com.myproject.boardback.service;

import org.springframework.http.ResponseEntity;

import com.myproject.boardback.dto.request.user.PatchNicknameRequestDTO;
import com.myproject.boardback.dto.request.user.PatchProfileImageRequestDTO;
import com.myproject.boardback.dto.response.user.GetSignInUserResponseDTO;
import com.myproject.boardback.dto.response.user.GetUserResponseDTO;
import com.myproject.boardback.dto.response.user.PatchNicknameResponseDTO;
import com.myproject.boardback.dto.response.user.PatchProfileImageResponseDTO;

public interface UserService {
  
  ResponseEntity<? super GetUserResponseDTO> getUser(String email);
  ResponseEntity<? super GetSignInUserResponseDTO> getSignInUser(String email);
  ResponseEntity<? super PatchNicknameResponseDTO> patchNickname(PatchNicknameRequestDTO dto, String email);
  ResponseEntity<? super PatchProfileImageResponseDTO> patchProfileImage(PatchProfileImageRequestDTO dto, String email);
}

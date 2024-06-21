package com.myproject.boardback.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myproject.boardback.dto.request.user.PatchNicknameRequestDTO;
import com.myproject.boardback.dto.request.user.PatchProfileImageRequestDTO;
import com.myproject.boardback.dto.response.ResponseDto;
import com.myproject.boardback.dto.response.user.GetSignInUserResponseDTO;
import com.myproject.boardback.dto.response.user.GetUserResponseDTO;
import com.myproject.boardback.dto.response.user.PatchNicknameResponseDTO;
import com.myproject.boardback.dto.response.user.PatchProfileImageResponseDTO;
import com.myproject.boardback.entity.UserEntity;
import com.myproject.boardback.repository.UserRepository;
import com.myproject.boardback.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService{
  
  private final UserRepository userRepository;

  @Override
  public ResponseEntity<? super GetUserResponseDTO> getUser(String email) {
    
    UserEntity userEntity = null;

    try {

      userEntity = userRepository.findByEmail(email);
      if (userEntity == null) return GetUserResponseDTO.noExistUser();
    }catch (Exception e){
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetUserResponseDTO.success(userEntity);
  }

  @Override
  public ResponseEntity<? super GetSignInUserResponseDTO> getSignInUser(String email) {

    UserEntity userEntity = null;

    try {

      userEntity = userRepository.findByEmail(email);
      if(userEntity == null) return GetSignInUserResponseDTO.noExistUser();
      
    }catch (Exception e){
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetSignInUserResponseDTO.success(userEntity);


  }

  @Override
  public ResponseEntity<? super PatchNicknameResponseDTO> patchNickname(PatchNicknameRequestDTO dto, String email) {
    try {

      UserEntity userEntity = userRepository.findByEmail(email);
      if (userEntity == null) PatchNicknameResponseDTO.noExistUser();

      String nickname = dto.getNickname();
      boolean existedNickname = userRepository.existsByNickname(nickname);
      if (existedNickname) return PatchNicknameResponseDTO.duplicateNickname();

      userEntity.setNickname(nickname);
      userRepository.save(userEntity);

    }catch (Exception e){
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return PatchNicknameResponseDTO.success();
  }

  @Override
  public ResponseEntity<? super PatchProfileImageResponseDTO> patchProfileImage(PatchProfileImageRequestDTO dto, String email) {
    try {

      UserEntity userEntity = userRepository.findByEmail(email);
      if( userEntity == null) return PatchProfileImageResponseDTO.noExistUser();

      String profileImage = dto.getProfileImage();
      userEntity.setProfileImage(profileImage);
      userRepository.save(userEntity);
    }catch (Exception e){
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return PatchProfileImageResponseDTO.success();
  }
}

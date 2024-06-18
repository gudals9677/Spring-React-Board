package com.myproject.boardback.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myproject.boardback.dto.response.ResponseDto;
import com.myproject.boardback.dto.response.user.GetSignInUserResponseDTO;
import com.myproject.boardback.entity.UserEntity;
import com.myproject.boardback.repository.UserRepository;
import com.myproject.boardback.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService{
  
  private final UserRepository userRepository;

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
}

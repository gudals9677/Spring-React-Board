package com.myproject.boardback.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myproject.boardback.dto.request.auth.SignInRequestDTO;
import com.myproject.boardback.dto.request.auth.SignUpRequestDTO;
import com.myproject.boardback.dto.response.ResponseDto;
import com.myproject.boardback.dto.response.auth.SignInResponseDTO;
import com.myproject.boardback.dto.response.auth.SignUpResponseDTO;
import com.myproject.boardback.entity.UserEntity;
import com.myproject.boardback.provider.JwtProvider;
import com.myproject.boardback.repository.UserRepository;
import com.myproject.boardback.service.AuthService;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService{

  private final UserRepository userRepository;
  private final JwtProvider jwtProvider;

  // security PasswordEncoder 정의
  private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Override
  public ResponseEntity<? super SignUpResponseDTO> signUp(SignUpRequestDTO dto) {
  

    try {
      // email, nickname, telNumber는 중복불가(정의)
      String email = dto.getEmail();
      boolean existedEmail = userRepository.existsByEmail(email);
      if(existedEmail) return SignUpResponseDTO.duplicateEmail();

      String nickname = dto.getNickname();
      boolean existedNickname = userRepository.existsByNickname(nickname);
      if(existedNickname) return SignUpResponseDTO.duplicateNickName();

      String telNumber = dto.getTelNumber();
      boolean existedTelNumber = userRepository.existsByTelNumber(telNumber);
      if(existedTelNumber) return SignUpResponseDTO.duplicateTelNumber();

      // 비밀번호 암호화
      String password = dto.getPassword();
      String encodedPassword = passwordEncoder.encode(password);
      dto.setPassword(encodedPassword);

      // 유저정보 db에 저장
      UserEntity userEntity = new UserEntity(dto);
      userRepository.save(userEntity);

    }catch(Exception e){
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return SignUpResponseDTO.success();
  }

  @Override
  public ResponseEntity<? super SignInResponseDTO> signIn(SignInRequestDTO dto) {
    
    String token = null;

    try {

      String email = dto.getEmail();
      UserEntity userEntity = userRepository.findByEmail(email);
      if (userEntity == null) return SignInResponseDTO.signInFail();

      String password = dto.getPassword();
      String encodedPassword = userEntity.getPassword();
      boolean isMatched = passwordEncoder.matches(password, encodedPassword);
      if (!isMatched) return SignInResponseDTO.signInFail();

      token = jwtProvider.create(email);
    }catch(Exception e){
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return SignInResponseDTO.success(token);
  }
  
}

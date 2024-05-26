package com.myproject.boardback.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myproject.boardback.common.ResponseCode;
import com.myproject.boardback.common.ResponseMessage;
import com.myproject.boardback.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class SignInResponseDTO extends ResponseDto{

  private String token;
  private int expirationTime;

  public SignInResponseDTO(String token) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.token = token;
    this.expirationTime = 3600;
  }
  
  public static ResponseEntity<SignInResponseDTO> success(String token){
    SignInResponseDTO result = new SignInResponseDTO(token);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  public static ResponseEntity<ResponseDto> signInFail() {
    ResponseDto result = new ResponseDto((ResponseCode.SIGN_IN_FAIL), ResponseMessage.SIGN_IN_FAIL);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
  }
}

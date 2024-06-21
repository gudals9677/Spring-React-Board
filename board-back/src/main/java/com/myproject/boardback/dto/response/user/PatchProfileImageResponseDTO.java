package com.myproject.boardback.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myproject.boardback.common.ResponseCode;
import com.myproject.boardback.common.ResponseMessage;
import com.myproject.boardback.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class PatchProfileImageResponseDTO extends ResponseDto{
  
  private PatchProfileImageResponseDTO(){
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
  }

  public static ResponseEntity<PatchProfileImageResponseDTO> success() {
    PatchProfileImageResponseDTO result = new PatchProfileImageResponseDTO();
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  public static ResponseEntity<ResponseDto> noExistUser() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER,ResponseMessage.NOT_EXISTED_USER);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }
}

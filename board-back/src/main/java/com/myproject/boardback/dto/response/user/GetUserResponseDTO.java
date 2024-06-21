package com.myproject.boardback.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myproject.boardback.common.ResponseCode;
import com.myproject.boardback.common.ResponseMessage;
import com.myproject.boardback.dto.response.ResponseDto;
import com.myproject.boardback.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetUserResponseDTO extends ResponseDto{

  private String email;
  private String nickname;
  private String profileImage;
  
  private GetUserResponseDTO(UserEntity userEntity) {

    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.email = userEntity.getEmail();
    this.nickname = userEntity.getNickname();
    this.profileImage = userEntity.getProfileImage();
  }

  public static ResponseEntity<GetUserResponseDTO> success(UserEntity userEntity){
    GetUserResponseDTO result = new GetUserResponseDTO(userEntity);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
  
  public static ResponseEntity<ResponseDto> noExistUser() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER,ResponseMessage.NOT_EXISTED_USER);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }
}

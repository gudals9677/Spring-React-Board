package com.myproject.boardback.dto.response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myproject.boardback.common.ResponseCode;
import com.myproject.boardback.common.ResponseMessage;
import com.myproject.boardback.dto.response.ResponseDto;

public class IncreaseViewCountResponseDTO extends ResponseDto{
  
  private IncreaseViewCountResponseDTO() {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
  }

  public static ResponseEntity<IncreaseViewCountResponseDTO> success() {
    IncreaseViewCountResponseDTO result = new IncreaseViewCountResponseDTO();
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
  public static ResponseEntity<ResponseDto> noExistBoard() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD,ResponseMessage.NOT_EXISTED_BOARD);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }
}

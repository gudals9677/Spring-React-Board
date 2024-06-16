package com.myproject.boardback.dto.response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myproject.boardback.common.ResponseCode;
import com.myproject.boardback.common.ResponseMessage;
import com.myproject.boardback.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class DeleteBoardResponseDTO extends ResponseDto{

  private DeleteBoardResponseDTO() {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
  }

  public static ResponseEntity<DeleteBoardResponseDTO> success() {
    DeleteBoardResponseDTO result = new DeleteBoardResponseDTO();
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  public static ResponseEntity<ResponseDto> noExistBoard() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }

  public static ResponseEntity<ResponseDto> noExistUser() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }

  public static ResponseEntity<ResponseDto> noPermission() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
  }
  
}

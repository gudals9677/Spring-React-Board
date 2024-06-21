package com.myproject.boardback.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myproject.boardback.common.ResponseCode;
import com.myproject.boardback.common.ResponseMessage;
import com.myproject.boardback.dto.object.BoardListItem;
import com.myproject.boardback.dto.response.ResponseDto;
import com.myproject.boardback.entity.BoardListViewEntity;

import lombok.Getter;

@Getter
public class GetUserBoardListResponseDTO extends ResponseDto{
  
  private List<BoardListItem> userBoardList;

  private GetUserBoardListResponseDTO(List<BoardListViewEntity> boardListViewEntities) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.userBoardList = BoardListItem.getList(boardListViewEntities);
  }

  public static ResponseEntity<GetUserBoardListResponseDTO> success(List<BoardListViewEntity> boardListViewEntities){
    GetUserBoardListResponseDTO result = new GetUserBoardListResponseDTO(boardListViewEntities);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  public static ResponseEntity<ResponseDto> noExistUser() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER,ResponseMessage.NOT_EXISTED_USER);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }
}

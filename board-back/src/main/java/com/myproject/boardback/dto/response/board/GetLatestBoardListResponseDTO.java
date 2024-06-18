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
public class GetLatestBoardListResponseDTO extends ResponseDto{

  private List<BoardListItem> lastestList;

  private GetLatestBoardListResponseDTO(List<BoardListViewEntity> boardEntities) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.lastestList = BoardListItem.getList(boardEntities);
  }

  public static ResponseEntity<GetLatestBoardListResponseDTO> success(List<BoardListViewEntity> boardEntities) {
    GetLatestBoardListResponseDTO result = new GetLatestBoardListResponseDTO(boardEntities);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
  
}

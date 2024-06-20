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
public class GetSearchBoardListResponseDTO extends ResponseDto {
  
  private List<BoardListItem> searchList;

  private GetSearchBoardListResponseDTO(List<BoardListViewEntity> boardListViewEntities) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.searchList = BoardListItem.getList(boardListViewEntities);
  }

  public static ResponseEntity<GetSearchBoardListResponseDTO> success(List<BoardListViewEntity> boardListViewEntities) {
    GetSearchBoardListResponseDTO result = new GetSearchBoardListResponseDTO(boardListViewEntities);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
}

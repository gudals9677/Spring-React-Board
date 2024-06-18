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
public class GetTop3BoardListResponseDTO extends ResponseDto{
  
  private List<BoardListItem> top3List;

  private GetTop3BoardListResponseDTO(List<BoardListViewEntity> boardListViewEntities) {

    super(ResponseCode.SUCCESS,ResponseMessage.SUCCESS);
    this.top3List = BoardListItem.getList(boardListViewEntities);
  }

  public static ResponseEntity<GetTop3BoardListResponseDTO> success(List<BoardListViewEntity> boardListViewEntities){
    GetTop3BoardListResponseDTO result = new GetTop3BoardListResponseDTO(boardListViewEntities);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
}

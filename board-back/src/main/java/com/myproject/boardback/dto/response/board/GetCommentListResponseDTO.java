package com.myproject.boardback.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myproject.boardback.common.ResponseCode;
import com.myproject.boardback.common.ResponseMessage;
import com.myproject.boardback.dto.object.CommentListItem;
import com.myproject.boardback.dto.response.ResponseDto;
import com.myproject.boardback.repository.resultSet.GetCommentListResultSet;

import lombok.Getter;

@Getter
public class GetCommentListResponseDTO extends ResponseDto{
  
  private List<CommentListItem> commentList;

  private GetCommentListResponseDTO(List<GetCommentListResultSet> resultSets) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.commentList = CommentListItem.copyList(resultSets);
  }

  public static ResponseEntity<GetCommentListResponseDTO> success(List<GetCommentListResultSet> resultSets){
    GetCommentListResponseDTO result = new GetCommentListResponseDTO(resultSets);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  public static ResponseEntity<ResponseDto> noExistBoard() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }
}

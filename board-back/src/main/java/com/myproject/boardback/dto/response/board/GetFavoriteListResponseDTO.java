package com.myproject.boardback.dto.response.board;

import com.myproject.boardback.common.ResponseCode;
import com.myproject.boardback.common.ResponseMessage;
import com.myproject.boardback.dto.object.FavoriteListItem;
import com.myproject.boardback.dto.response.ResponseDto;
import com.myproject.boardback.repository.resultSet.GetFavortieListResultSet;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class GetFavoriteListResponseDTO extends ResponseDto{

  private List<FavoriteListItem> favoriteList;

  private GetFavoriteListResponseDTO(List<GetFavortieListResultSet> resultSets) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.favoriteList = FavoriteListItem.copyList(resultSets);
  }

  public static ResponseEntity<GetFavoriteListResponseDTO> success(List<GetFavortieListResultSet> resultSets) {
    GetFavoriteListResponseDTO result = new GetFavoriteListResponseDTO(resultSets);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  public static ResponseEntity<ResponseDto> noExistBoard() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD,ResponseMessage.NOT_EXISTED_USER);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result); 
  }
  
}

package com.myproject.boardback.dto.response.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myproject.boardback.common.ResponseCode;
import com.myproject.boardback.common.ResponseMessage;
import com.myproject.boardback.dto.response.ResponseDto;
import com.myproject.boardback.repository.resultSet.GetPopularListResultSet;

import lombok.Getter;

@Getter
public class GetPopularListResponseDTO extends ResponseDto{

  private List<String> popularWordList;

  private GetPopularListResponseDTO(List<GetPopularListResultSet> resultSets) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

    List<String> popularWordList = new ArrayList<>();
    for(GetPopularListResultSet resultSet : resultSets) {
      String popularWord = resultSet.getSearchWord();
        popularWordList.add(popularWord);
    }

    this.popularWordList = popularWordList;
  }

  public static ResponseEntity<GetPopularListResponseDTO> success(List<GetPopularListResultSet> resultSets) {
    GetPopularListResponseDTO result = new GetPopularListResponseDTO(resultSets);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
  
}

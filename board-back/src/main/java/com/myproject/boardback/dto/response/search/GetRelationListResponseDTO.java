package com.myproject.boardback.dto.response.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myproject.boardback.common.ResponseCode;
import com.myproject.boardback.common.ResponseMessage;
import com.myproject.boardback.dto.response.ResponseDto;
import com.myproject.boardback.repository.resultSet.GetRelationListResultSet;

import lombok.Getter;

@Getter
public class GetRelationListResponseDTO extends ResponseDto{

  private List<String> relativeWordList;

  private GetRelationListResponseDTO(List<GetRelationListResultSet> resultSets) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

    List<String> relativeWordList = new ArrayList<>();
    for (GetRelationListResultSet resultSet: resultSets){
      String relativeWord = resultSet.getSearchWord();
      relativeWordList.add(relativeWord);
    }

    this.relativeWordList = relativeWordList;
  }

  public static ResponseEntity<GetRelationListResponseDTO> success(List<GetRelationListResultSet> resultSets) {
    GetRelationListResponseDTO result = new GetRelationListResponseDTO(resultSets);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
}

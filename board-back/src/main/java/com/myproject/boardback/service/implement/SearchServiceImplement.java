package com.myproject.boardback.service.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myproject.boardback.dto.response.ResponseDto;
import com.myproject.boardback.dto.response.search.GetPopularListResponseDTO;
import com.myproject.boardback.dto.response.search.GetRelationListResponseDTO;
import com.myproject.boardback.repository.SearchLogRepository;
import com.myproject.boardback.repository.resultSet.GetPopularListResultSet;
import com.myproject.boardback.repository.resultSet.GetRelationListResultSet;
import com.myproject.boardback.service.SearchService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImplement implements SearchService{

  private final SearchLogRepository searchLogRepository;

  @Override
  public ResponseEntity<? super GetPopularListResponseDTO> getPopularList() {
    List<GetPopularListResultSet> resultSets = new ArrayList<>();

    try {
    
      resultSets = searchLogRepository.getPopularList();
    } catch (Exception e){
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return GetPopularListResponseDTO.success(resultSets);
  }

  @Override
  public ResponseEntity<? super GetRelationListResponseDTO> getRelationList(String searchWord) {
    
    List<GetRelationListResultSet> resultSets = new ArrayList<>();

    try {

      resultSets = searchLogRepository.getRelationList(searchWord);

    }catch(Exception e){
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return GetRelationListResponseDTO.success(resultSets);
  }

}

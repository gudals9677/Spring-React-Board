package com.myproject.boardback.service;

import org.springframework.http.ResponseEntity;

import com.myproject.boardback.dto.response.search.GetPopularListResponseDTO;
import com.myproject.boardback.dto.response.search.GetRelationListResponseDTO;

public interface SearchService {
  
  ResponseEntity<? super GetPopularListResponseDTO> getPopularList();
  ResponseEntity<? super GetRelationListResponseDTO> getRelationList(String searchWord);
}

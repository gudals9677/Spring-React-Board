package com.myproject.boardback.service;

import org.springframework.http.ResponseEntity;

import com.myproject.boardback.dto.response.search.GetPopularListResponseDTO;

public interface SearchService {
  
  ResponseEntity<? super GetPopularListResponseDTO> getPopularList();
}

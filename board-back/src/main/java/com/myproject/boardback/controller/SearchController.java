package com.myproject.boardback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.boardback.dto.response.search.GetPopularListResponseDTO;
import com.myproject.boardback.dto.response.search.GetRelationListResponseDTO;
import com.myproject.boardback.service.SearchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value="/api/v1/search")
@RequiredArgsConstructor
public class SearchController {
  
  private final SearchService searchService;

  @GetMapping("/popular-list")
  public ResponseEntity<? super GetPopularListResponseDTO> getPopularList() {
    ResponseEntity<? super GetPopularListResponseDTO> response = searchService.getPopularList();
    return response;
  }

  @GetMapping("/{searchWord}/relation-list")
  public ResponseEntity<? super GetRelationListResponseDTO> getRelationList(
    @PathVariable("searchWord") String searchWord
  ) {
    ResponseEntity<? super GetRelationListResponseDTO> response = searchService.getRelationList(searchWord);
    return response;
  }
  
  
}

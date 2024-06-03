package com.myproject.boardback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.boardback.dto.request.board.PostBoardRequestDTO;
import com.myproject.boardback.dto.response.board.PostBoardResponseDTO;
import com.myproject.boardback.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @PostMapping("")
  public ResponseEntity<? super PostBoardResponseDTO> postBoard (
    @RequestBody @Valid PostBoardRequestDTO requestBody,
     @AuthenticationPrincipal String email){

      ResponseEntity<? super PostBoardResponseDTO> response = boardService.postBoard(requestBody, email);
      return response;
  }
  
}

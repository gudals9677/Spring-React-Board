package com.myproject.boardback.service;

import org.springframework.http.ResponseEntity;

import com.myproject.boardback.dto.request.board.PostBoardRequestDTO;
import com.myproject.boardback.dto.response.board.PostBoardResponseDTO;

public interface BoardService {
  ResponseEntity<? super PostBoardResponseDTO> postBoard(PostBoardRequestDTO dto, String email);
}

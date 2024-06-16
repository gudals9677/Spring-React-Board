package com.myproject.boardback.service;

import org.springframework.http.ResponseEntity;

import com.myproject.boardback.dto.request.board.PostBoardRequestDTO;
import com.myproject.boardback.dto.request.board.PostCommentRequestDTO;
import com.myproject.boardback.dto.response.board.DeleteBoardResponseDTO;
import com.myproject.boardback.dto.response.board.GetBoardResponseDTO;
import com.myproject.boardback.dto.response.board.GetCommentListResponseDTO;
import com.myproject.boardback.dto.response.board.GetFavoriteListResponseDTO;
import com.myproject.boardback.dto.response.board.IncreaseViewCountResponseDTO;
import com.myproject.boardback.dto.response.board.PostBoardResponseDTO;
import com.myproject.boardback.dto.response.board.PostCommentResponseDTO;
import com.myproject.boardback.dto.response.board.PutFavoriteResponseDTO;

public interface BoardService {
  ResponseEntity<? super GetBoardResponseDTO> getBoard(Integer boardNumber);
  ResponseEntity<? super GetFavoriteListResponseDTO> getFavoriteList(Integer boardNumber);
  ResponseEntity<? super GetCommentListResponseDTO> getCommentList(Integer boardNumber);

  ResponseEntity<? super PostBoardResponseDTO> postBoard(PostBoardRequestDTO dto, String email);
  ResponseEntity<? super PostCommentResponseDTO> postComment(PostCommentRequestDTO dto,Integer boardNumber, String email);

  ResponseEntity<? super PutFavoriteResponseDTO> putFavorite(Integer boardNumber, String email);

  ResponseEntity<? super IncreaseViewCountResponseDTO> increaseViewCount(Integer boardNumber);

  ResponseEntity<? super DeleteBoardResponseDTO> deleteBoard(Integer boardNumber, String email);
}

package com.myproject.boardback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.boardback.dto.request.board.PostBoardRequestDTO;
import com.myproject.boardback.dto.request.board.PostCommentRequestDTO;
import com.myproject.boardback.dto.response.board.GetBoardResponseDTO;
import com.myproject.boardback.dto.response.board.GetCommentListResponseDTO;
import com.myproject.boardback.dto.response.board.GetFavoriteListResponseDTO;
import com.myproject.boardback.dto.response.board.IncreaseViewCountResponseDTO;
import com.myproject.boardback.dto.response.board.PostBoardResponseDTO;
import com.myproject.boardback.dto.response.board.PostCommentResponseDTO;
import com.myproject.boardback.dto.response.board.PutFavoriteResponseDTO;
import com.myproject.boardback.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  // 게시글 조회
  @GetMapping("/{boardNumber}")
  public ResponseEntity<? super GetBoardResponseDTO> getBoard(
    @PathVariable("boardNumber") Integer boardNumber
    ){
    ResponseEntity<? super GetBoardResponseDTO> response = boardService.getBoard(boardNumber);
    return response;
  }

  // 게시글 좋아요 리스트 조회
  @GetMapping("/{boardNumber}/favorite-list")
  public ResponseEntity<? super GetFavoriteListResponseDTO> getFavoriteList(
    @PathVariable("boardNumber") Integer boardNumber
  ){
    ResponseEntity<? super GetFavoriteListResponseDTO> response = boardService.getFavoriteList(boardNumber);
    return response;
  }
  
  // 게시글 댓글 리스트 불러오기
  @GetMapping("/{boardNumber}/comment-list")
  public ResponseEntity<? super GetCommentListResponseDTO> getCommentList(
    @PathVariable("boardNumber") Integer boardNumber
  ) {
    ResponseEntity<? super GetCommentListResponseDTO> response = boardService.getCommentList(boardNumber);
    return response;
  }
  // 게시글 조회수 업
  @GetMapping("/{boardNumber}/increase-view-count")
  public ResponseEntity<? super IncreaseViewCountResponseDTO> increaseViewCount(
    @PathVariable("boardNumber") Integer boardNumber
  ){
    ResponseEntity<? super IncreaseViewCountResponseDTO> response = boardService.increaseViewCount(boardNumber);
    return response;
  }
  

  // 게시글 작성
  @PostMapping("")
  public ResponseEntity<? super PostBoardResponseDTO> postBoard (
    @RequestBody @Valid PostBoardRequestDTO requestBody,
     @AuthenticationPrincipal String email){

      ResponseEntity<? super PostBoardResponseDTO> response = boardService.postBoard(requestBody, email);
      return response;
  }

  // 게시글 댓글 작성
  @PostMapping("/{boardNumber}/comment")
  public ResponseEntity<? super PostCommentResponseDTO> postComment(
    @RequestBody @Valid PostCommentRequestDTO requestBody,
    @PathVariable("boardNumber") Integer boardNumber,
    @AuthenticationPrincipal String email
  ) {
    ResponseEntity<? super PostCommentResponseDTO> response = boardService.postComment(requestBody, boardNumber, email);
    return response;
  }
  
  // 게시글 좋아요 기능
  @PutMapping("/{boardNumber}/favorite")
  public ResponseEntity<? super PutFavoriteResponseDTO> putFavorite(
      @PathVariable("boardNumber") Integer boardNumber,
      @AuthenticationPrincipal String email
  ) {
        ResponseEntity<? super PutFavoriteResponseDTO> response = boardService.putFavorite(boardNumber, email);
        return response;
  }

}

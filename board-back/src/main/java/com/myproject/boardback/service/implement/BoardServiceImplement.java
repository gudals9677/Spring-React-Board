package com.myproject.boardback.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myproject.boardback.dto.request.board.PostBoardRequestDTO;
import com.myproject.boardback.dto.request.board.PostCommentRequestDTO;
import com.myproject.boardback.dto.response.ResponseDto;
import com.myproject.boardback.dto.response.board.GetBoardResponseDTO;
import com.myproject.boardback.dto.response.board.GetCommentListResponseDTO;
import com.myproject.boardback.dto.response.board.GetFavoriteListResponseDTO;
import com.myproject.boardback.dto.response.board.IncreaseViewCountResponseDTO;
import com.myproject.boardback.dto.response.board.PostBoardResponseDTO;
import com.myproject.boardback.dto.response.board.PostCommentResponseDTO;
import com.myproject.boardback.dto.response.board.PutFavoriteResponseDTO;
import com.myproject.boardback.entity.BoardEntity;
import com.myproject.boardback.entity.CommentEntity;
import com.myproject.boardback.entity.FavoriteEntity;
import com.myproject.boardback.entity.ImageEntity;
import com.myproject.boardback.repository.BoardRepository;
import com.myproject.boardback.repository.CommentRepository;
import com.myproject.boardback.repository.FavoriteRepository;
import com.myproject.boardback.repository.ImageRepository;
import com.myproject.boardback.repository.UserRepository;
import com.myproject.boardback.repository.resultSet.GetBoardResultSet;
import com.myproject.boardback.repository.resultSet.GetCommentListResultSet;
import com.myproject.boardback.repository.resultSet.GetFavortieListResultSet;
import com.myproject.boardback.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

  private final UserRepository userRepository;
  private final BoardRepository boardRepository;
  private final ImageRepository imageRepository;
  private final FavoriteRepository favoriteRepository;
  private final CommentRepository commentRepository;

  // 게시글 불러오기
  @Override
  public ResponseEntity<? super GetBoardResponseDTO> getBoard(Integer boardNumber) {
    
    GetBoardResultSet resultSet = null;
    List<ImageEntity> imageEntities = new ArrayList<>();
    try {
      resultSet = boardRepository.getBoard(boardNumber);
      if(resultSet == null) return GetBoardResponseDTO.noExistBoard();
      
      imageEntities = imageRepository.findByBoardNumber(boardNumber);

    } catch (Exception e){
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetBoardResponseDTO.success(resultSet,imageEntities);
  }
  // 게시글 좋아요 리스트 불러오기
  @Override
  public ResponseEntity<? super GetFavoriteListResponseDTO> getFavoriteList(Integer boardNumber) {
    
    List<GetFavortieListResultSet> resultSets = new ArrayList<>();
    try {

      boolean existedBoard = boardRepository.existsByBoardNumber(boardNumber);
      if (!existedBoard) return GetFavoriteListResponseDTO.noExistBoard();

      resultSets = favoriteRepository.getFavoriteList(boardNumber);
      
    } catch(Exception e){
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetFavoriteListResponseDTO.success(resultSets);
  }
  
  // 게시물 댓글 불러오기
  @Override
  public ResponseEntity<? super GetCommentListResponseDTO> getCommentList(Integer boardNumber) {
    
    List<GetCommentListResultSet> resultSets = new ArrayList<>();

    try {

      boolean existedBoard = boardRepository.existsByBoardNumber(boardNumber);
      if(!existedBoard) return GetCommentListResponseDTO.noExistBoard();

      resultSets = commentRepository.getCommentList(boardNumber);

    } catch(Exception e){
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return GetCommentListResponseDTO.success(resultSets);
  }

  // 게시글 작성
  @Override
  public ResponseEntity<? super PostBoardResponseDTO> postBoard(PostBoardRequestDTO dto, String email) {
  
    try {

      // user가 존재하는지 검증하고 Email조회
      boolean existedEmail = userRepository.existsByEmail(email);
      if (!existedEmail) return PostBoardResponseDTO.notExistUser();

      // 게시글 작성 메서드 엔티티에 정의
      BoardEntity boardEntity = new BoardEntity(dto, email);
      boardRepository.save(boardEntity);

      // save되면 boardNumber가 자동으로 생성됨
      int boardNumber = boardEntity.getBoardNumber();
      // boardImageList를 boardList에 담아줌
      List<String> boardImageList = dto.getBoardImageList();
      List<ImageEntity> imageEntities = new ArrayList<>();

      // List인 이미지목록을 반복조회
      for (String image: boardImageList) {
        ImageEntity imageEntity = new ImageEntity(boardNumber, image);
        imageEntities.add(imageEntity);
      }

      imageRepository.saveAll(imageEntities);

    } catch(Exception e){
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    
    return PostBoardResponseDTO.success();
  }

  // 댓글 작성 로직
  @Override
  public ResponseEntity<? super PostCommentResponseDTO> postComment(PostCommentRequestDTO dto,Integer boardNumber ,String email) {
    
    try {
      
      BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
      if(boardEntity == null) return PostCommentResponseDTO.noExistBoard();

      boolean existedUser = userRepository.existsByEmail(email);
      if (!existedUser) return PostCommentResponseDTO.noExistUser();

      CommentEntity commentEntity = new CommentEntity(dto, boardNumber, email);
      commentRepository.save(commentEntity);

      boardEntity.increaseCommentCount();
      boardRepository.save(boardEntity);


    } catch(Exception e){
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return PostCommentResponseDTO.success();
  }
  // 게시글 좋아요 기능
  @Override
  public ResponseEntity<? super PutFavoriteResponseDTO> putFavorite(Integer boardNumber, String email) {
    
    try {
      
      boolean existedUser = userRepository.existsByEmail(email);
      if (!existedUser) return PutFavoriteResponseDTO.noExistUser();

      BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
      if (boardEntity == null ) return PutFavoriteResponseDTO.noExistBoard();

      FavoriteEntity favoriteEntity = favoriteRepository.findByBoardNumberAndUserEmail(boardNumber, email);
      if(favoriteEntity == null){
        favoriteEntity = new FavoriteEntity(email, boardNumber);
        favoriteRepository.save(favoriteEntity);
        boardEntity.increaseFavoriteCount();
      }
      else {
        favoriteRepository.delete(favoriteEntity);
        boardEntity.decreaseFavoriteCount();
      }

      boardRepository.save(boardEntity);
      
    } catch (Exception e){
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return PutFavoriteResponseDTO.success();
  }
  @Override
  public ResponseEntity<? super IncreaseViewCountResponseDTO> increaseViewCount(Integer boardNumber) {
    
    try {
      
      BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
      if (boardEntity == null) return IncreaseViewCountResponseDTO.noExistBoard();

      boardEntity.increaseViewCount();
      boardRepository.save(boardEntity);

    }catch(Exception e){
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return IncreaseViewCountResponseDTO.success();
  }

}

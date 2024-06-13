package com.myproject.boardback.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myproject.boardback.dto.request.board.PostBoardRequestDTO;
import com.myproject.boardback.dto.response.ResponseDto;
import com.myproject.boardback.dto.response.board.GetBoardResponseDTO;
import com.myproject.boardback.dto.response.board.PostBoardResponseDTO;
import com.myproject.boardback.entity.BoardEntity;
import com.myproject.boardback.entity.ImageEntity;
import com.myproject.boardback.repository.BoardRepository;
import com.myproject.boardback.repository.ImageRepository;
import com.myproject.boardback.repository.UserRepository;
import com.myproject.boardback.repository.resultSet.GetBoardResultSet;
import com.myproject.boardback.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

  private final UserRepository userRepository;
  private final BoardRepository boardRepository;
  private final ImageRepository imageRepository;

  
  @Override
  public ResponseEntity<? super GetBoardResponseDTO> getBoard(Integer boardNumber) {
    
    GetBoardResultSet resultSet = null;
    List<ImageEntity> imageEntities = new ArrayList<>();
    try {
      resultSet = boardRepository.getBoard(boardNumber);
      if(resultSet == null) return GetBoardResponseDTO.noExistBoard();
      
      imageEntities = imageRepository.findByBoardNumber(boardNumber);

      BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
      boardEntity.increaseViewCount();
      boardRepository.save(boardEntity);

    } catch (Exception e){
      e.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetBoardResponseDTO.success(resultSet,imageEntities);
  }

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

  

}

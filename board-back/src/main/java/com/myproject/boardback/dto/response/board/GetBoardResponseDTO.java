package com.myproject.boardback.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myproject.boardback.common.ResponseCode;
import com.myproject.boardback.common.ResponseMessage;
import com.myproject.boardback.dto.response.ResponseDto;
import com.myproject.boardback.entity.ImageEntity;
import com.myproject.boardback.repository.resultSet.GetBoardResultSet;

import lombok.Getter;

@Getter
public class GetBoardResponseDTO extends ResponseDto {

  private int boardNumber;
  private String title;
  private String content;
  private List<String> boardImageList;
  private String writeDatetime;
  private String writerEmail;
  private String writerNickname;
  private String writerProfileImage;

  private GetBoardResponseDTO(GetBoardResultSet resultSet, List<ImageEntity> imageEntities) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

    List<String> boardImageList = new ArrayList<>();
    for (ImageEntity imageEntity: imageEntities){
      String boardImage = imageEntity.getImage();
      boardImageList.add(boardImage);
    }

    this.boardNumber = resultSet.getBoardNumber();
    this.title = resultSet.getTitle();
    this.content = resultSet.getContent();
    this.boardImageList = boardImageList;
    this.writeDatetime = resultSet.getWriteDatetime();
    this.writerEmail = resultSet.getWriterEmail();
    this.writerNickname = resultSet.getWriterNickname();
    this.writerProfileImage = resultSet.getWriterProfileImage();
  }

  public static ResponseEntity<GetBoardResponseDTO> success(GetBoardResultSet resultSet, List<ImageEntity> imageEntities) {
    GetBoardResponseDTO result = new GetBoardResponseDTO(resultSet, imageEntities);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  public static ResponseEntity<ResponseDto> noExistBoard() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD,ResponseMessage.NOT_EXISTED_BOARD);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }

  
}

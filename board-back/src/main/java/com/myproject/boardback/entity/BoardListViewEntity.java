package com.myproject.boardback.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="board_list_view")
@Table(name="board_list_view")
public class BoardListViewEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int boardNumber;
  private String title;
  private String content;
  private String titleImage;
  private int favoriteCount;
  private int commentCount;
  private int viewCount;
  private String writeDatetime;
  private String writerEmail;
  private String writerNickname;
  private String writerProfileImage;

  
}

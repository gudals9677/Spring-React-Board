package com.myproject.boardback.entity.primaryKey;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(FavoritePk.class)
public class FavoritePk implements Serializable{
  @Column(name="user_email")
  private String userEmail;
  @Column(name="board_numer")
  private int boardNumber;
  
}

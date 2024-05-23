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
@Entity(name="serach_log")
@Table(name="serach_log")
public class SearchLogEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int sequence;
  private String searchWord;
  private String relationWord;
  private boolean relation;
}

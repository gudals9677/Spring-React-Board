package com.myproject.boardback.dto.object;

import com.myproject.boardback.repository.resultSet.GetFavortieListResultSet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteListItem {
  
  private String email;
  private String nickname;
  private String profileImage;

  public FavoriteListItem(GetFavortieListResultSet resultSet){
    this.email = resultSet.getEmail();
    this.nickname = resultSet.getNickname();
    this.profileImage = resultSet.getProfileImage();
  }
  public static List<FavoriteListItem> copyList(List<GetFavortieListResultSet> resultSets){
    List<FavoriteListItem> list = new ArrayList<>();
    for (GetFavortieListResultSet resultSet: resultSets) {
      FavoriteListItem favoriteListItem = new FavoriteListItem(resultSet);
      list.add(favoriteListItem);
    }
    return list;
  }
}

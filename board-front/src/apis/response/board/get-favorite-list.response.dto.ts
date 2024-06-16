import { FavoriteListItem } from "types/interface";
import ResponseDto from "../response.dto";

export default interface GetFavoriteListResponseDTO extends ResponseDto{
  favoriteList : FavoriteListItem[]
}
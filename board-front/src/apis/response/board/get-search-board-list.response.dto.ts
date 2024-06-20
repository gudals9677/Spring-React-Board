import { BoardListItem } from "types/interface";
import ResponseDto from "../response.dto";

export default interface GetSearchBoardListResponseDTO extends ResponseDto {
  searchList: BoardListItem[];
}
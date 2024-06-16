import { CommentListItem } from "types/interface";
import ResponseDto from "../response.dto";

export default interface GetCommentListResponseDTO extends ResponseDto {
  commentList: CommentListItem[];
}
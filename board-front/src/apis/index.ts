import axios from "axios";
import { SignInReqeustDTO, SignUpRequestDto } from "./request/auth";
import { SignInResponseDTO, SignUpResponseDto } from "./response/auth";
import { ResponseDto } from "./response";
import { error } from "console";
import { GetSignInUserResponseDTO, GetUserResponseDTO, PatchNicknameResponseDTO, PatchProfileImageResponseDTO } from "./response/user";
import { PatchBoardRequestDTO, PostBoardRequestDTO, PostCommentRequestDTO } from "./request/board";
import { PostBoardResponseDTO, GetBoardResponseDTO, IncreaseViewCountResponseDTO, GetFavoriteListResponseDTO, GetCommentListResponseDTO, PutFavoriteResponseDTO, PostCommentResponseDTO, DeleteBoardResponseDTO, PatchBoardResponseDTO, GetLatestBoardListResponseDTO, GetTop3BoardListResponseDTO, GetSearchBoardListResponseDTO, GetUserBoardListResponseDTO } from "./response/board";
import { GetPopularListResponseDTO, GetRelationListResponseDTO } from "./response/search";
import { PatchNicknameRequestDTO, PatchProfileImageRequestDTO } from "./request/user";

const DOMAIN = 'http://localhost:4000';

const API_DOMAIN = `${DOMAIN}/api/v1`;

const authorization = (accessToken: string) => {
   return {headers: {Authorization: `Bearer ${accessToken}`} }
};

const SIGN_IN_URL = () => `${API_DOMAIN}/auth/sign-in`;
const SIGN_UP_URL = () => `${API_DOMAIN}/auth/sign-up`;

export const signInRequest = async (requestBody: SignInReqeustDTO) => {
  const result = await axios.post(SIGN_IN_URL(), requestBody)
      .then(response => {
        const responseBody: SignInResponseDTO = response.data;
        return responseBody;
      })
      .catch(error => {
        if(!error.response.data) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      });
    return result;
}
export const signUpRequest = async (requestBody: SignUpRequestDto) => {
  const result = await axios.post(SIGN_UP_URL(), requestBody)
    .then(response => {
      const responseBody: SignUpResponseDto = response.data;
      return responseBody;
    })
    .catch(error => {
      if(!error.response.data) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
    return result;
}

const GET_BOARD_URL = (boardNumber: number | string) => `${API_DOMAIN}/board/${boardNumber}`;
const GET_LATEST_BOARD_LIST_URL = () => `${API_DOMAIN}/board/latest-list`;
const GET_TOP_3_BOARD_LIST_URL = () =>  `${API_DOMAIN}/board/top-3`;
const GET_SEARCH_BOARD_LIST_URL = (searchWord: string, preSearchWord: string | null) => `${API_DOMAIN}/board/search-list/${searchWord}${preSearchWord ? '/' + preSearchWord : ''}`;
const GET_USER_BOARD_LIST_URL = (email: string) => `${API_DOMAIN}/board/user-board-list/${email}`;
const INCREASE_VIEW_COUNT_URL = (boardNumber: number | string) => `${API_DOMAIN}/board/${boardNumber}/increase-view-count`;
const GET_FAVORITE_LIST_URL = (boardNumber: number | string) => `${API_DOMAIN}/board/${boardNumber}/favorite-list`;
const GET_COMMENT_LIST_URL = (boardNumber: number | string) => `${API_DOMAIN}/board/${boardNumber}/comment-list`;
const POST_BOARD_URL = () => `${API_DOMAIN}/board`;
const POST_COMMENT_URL = (boardNumber: number | string) =>  `${API_DOMAIN}/board/${boardNumber}/comment`;
const PATCH_BOARD_URL =  (boardNumber: number | string) => `${API_DOMAIN}/board/${boardNumber}`;
const PUT_FAVORITE_URL = (boardNumber: number | string) => `${API_DOMAIN}/board/${boardNumber}/favorite`;
const DELETE_BOARD_URL = (boardNumber: number | string) => `${API_DOMAIN}/board/${boardNumber}`;

export const getBoardRequest = async (boardNumber: number | string) => {
  const result = await axios.get(GET_BOARD_URL(boardNumber))
    .then(response => {
      const responseBody: GetBoardResponseDTO = response.data;
      return responseBody;
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    })
    return result;
}

export const getLatestBoardListRequest = async () => {
  const result = await axios.get(GET_LATEST_BOARD_LIST_URL())
    .then(response => {
      const responseBody: GetLatestBoardListResponseDTO = response.data;
      console.log('Response Body:', responseBody); // 응답 데이터 로그
      return responseBody;
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
    return result;
};

export const getTop3BoardListRequest = async () => {
  const result = await axios.get(GET_TOP_3_BOARD_LIST_URL())
    .then(response => {
      const responseBody: GetTop3BoardListResponseDTO = response.data;
      return responseBody;
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
    return result;
};

export const getSearchBoardListRequest = async(searchWord: string, preSearchWord: string | null) => {
  const result = await axios.get(GET_SEARCH_BOARD_LIST_URL(searchWord, preSearchWord))
  .then(response => {
    const responseBody: GetSearchBoardListResponseDTO = response.data;
    return responseBody;
  })
  .catch(error => {
    if(!error.response) return null;
    const responseBody: ResponseDto = error.response.data;
    return responseBody;
  });
  return result;
};

export const getUserBoardListRequest = async (email:string) => {
  const result = await axios.get(GET_USER_BOARD_LIST_URL(email))
  .then(response => {
    const responseBody: GetUserBoardListResponseDTO = response.data;
    return responseBody;
  })
  .catch(error => {
    if(!error.response) return null;
    const responseBody: ResponseDto = error.response.data;
    return responseBody;
  });
  return result;
}

export const getCommentListRequest = async (boardNumber: number | string) => {
  const result = await axios.get(GET_COMMENT_LIST_URL(boardNumber))
    .then(response => {
      const responseBody: GetCommentListResponseDTO = response.data;
      return responseBody;
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
    return result;
}


export const getFavoriteListRequest = async (boardNumber: number | string) => {
  const result = await axios.get(GET_FAVORITE_LIST_URL(boardNumber))
    .then(response => {
      const responseBody: GetFavoriteListResponseDTO = response.data;
      return responseBody;
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
    return result;
}

export const increaseViewCountRequest = async (boardNumber: number | string) => {
  const result = await axios.get(INCREASE_VIEW_COUNT_URL(boardNumber))
    .then(response => {
      const responseBody: IncreaseViewCountResponseDTO = response.data;
      return responseBody;
    })
    .catch(error => {
      if (error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    })
    return result;
}

export const postBoardRequest = async(requestBody: PostBoardRequestDTO, accessToken:string) => {
  const result = await axios.post(POST_BOARD_URL(), requestBody, authorization(accessToken))
    .then(response => {
      const responseBody: PostBoardResponseDTO = response.data;
      return responseBody;
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    })
    return result;
}

export const postCommentRequest = async(boardNumber: number | string,requestBody: PostCommentRequestDTO, accessToken: string) => {
  const result = await axios.post(POST_COMMENT_URL(boardNumber), requestBody, authorization(accessToken))
    .then(response => {
      const responseBody: PostCommentResponseDTO = response.data;
      return responseBody;
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    })
    return result;
}

export const PatchBoardRequest = async (boardNumber: number | string, requestBody: PatchBoardRequestDTO, accessToken: string) => {
  const result = await axios.patch(PATCH_BOARD_URL(boardNumber), requestBody, {
    headers: {
      'Authorization': `Bearer ${accessToken}`,
      'Content-Type': 'application/json'
      
    }
  })
  .then(response => {
    const responseBody: PatchBoardResponseDTO = response.data;
    console.log("responseBody체크:", responseBody)
    return responseBody;
  })
  .catch(error => {
    if(!error.response) return null;
    const responseBody: ResponseDto = error.response.data;
    return responseBody;
  })
  return result;
}

export const putFavoriteRequest = async (boardNumber: number | string, accessToken: string) => {
  const result = await axios.put(PUT_FAVORITE_URL(boardNumber), {}, authorization(accessToken))
    .then(response => {
      const responseBody: PutFavoriteResponseDTO = response.data;
      return responseBody;
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    })
    return result;
}

export const deleteBoardRequest = async (boardNumber: number | string, accessToken: string) => {
  const result = await axios.delete(DELETE_BOARD_URL(boardNumber), authorization(accessToken))
  .then(response => {
    const responseBody: DeleteBoardResponseDTO = response.data;
    return responseBody;
  })
  .catch(error => {
    if(!error.response) return null;
    const responseBody: ResponseDto = error.response.data;
    return responseBody;
  })
  return result;
}

const GET_POPULAR_LIST_URL = () => `${API_DOMAIN}/search/popular-list`;
const GET_RELATION_LIST_URL = (searchWord: string) => `${API_DOMAIN}/search/${searchWord}/relation-list`;

export const getPopularListRequest = async() => {
  const result = await axios.get(GET_POPULAR_LIST_URL())
    .then(response => {
      const responseBody: GetPopularListResponseDTO = response.data;
      return responseBody;
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
    return result;
}

export const getRelationListRequest = async(searchWord: string) => {
  const result = await axios.get(GET_RELATION_LIST_URL(searchWord))
  .then(response => {
    const responseBody: GetRelationListResponseDTO = response.data;
    return responseBody;
  })
  .catch(error => {
    if(!error.response) return null;
    const responseBody: ResponseDto = error.response.data;
    return responseBody;
  });
  return result;
}

const GET_USER_URL = (email: string) => `${API_DOMAIN}/user/${email}`;
const GET_SIGN_IN_USER_URL = () => `${API_DOMAIN}/user`;
const PATCH_NICKNAME_URL = () => `${API_DOMAIN}/user/nickname`;
const PATCH_PROFILE_IMAGE_URL = () => `${API_DOMAIN}/user/profile-image`;

export const getUserRequest = async (email: string) => {
  const result = await axios.get(GET_USER_URL(email))
    .then(response => {
      const responseBody: GetUserResponseDTO = response.data;
      return responseBody;
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
  return result;
}

export const getSignInUserReqeust = async (accessToken: string) => {
  const result = await axios.get(GET_SIGN_IN_USER_URL(), authorization(accessToken))
    .then(response => {
      const responseBody: GetSignInUserResponseDTO = response.data;
      return responseBody;
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
  return result;
}

export const patchNicknameRequest = async (requestBody: PatchNicknameRequestDTO, accessToken: string) => {
  const result = await axios.patch(PATCH_NICKNAME_URL(), requestBody, authorization(accessToken))
    .then(response => {
      const responseBody: PatchNicknameResponseDTO = response.data;
      return responseBody;
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
  return result;
}

export const patchProfileImageRequest = async (requestBody: PatchProfileImageRequestDTO, accessToken: string) => {
  const result = await axios.patch(PATCH_PROFILE_IMAGE_URL(), requestBody, authorization(accessToken))
    .then(response => {
      const responseBody: PatchProfileImageResponseDTO = response.data;
      return responseBody;
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
  return result;
}

const FILE_DOMAIN = `${DOMAIN}/file`;

const FILE_UPLOAD_URL = () => `${FILE_DOMAIN}/upload`;

const multipartFormData =  { headers: {'Content-Type': 'multipart/form-data'}};

export const fileUploadRequest = async (data: FormData) => {
  const result = await axios.post(FILE_UPLOAD_URL(), data,multipartFormData)
    .then(response => {
      const responseBody: string = response.data;
      return responseBody;
    })
    .catch(error => {
      return null;
    })
    return result;
}
package com.myproject.boardback.common;

public interface ResponseMessage {
  String SUCCESS = "Success.";              // 성공
  String VALIDATION_FAILED= "Validation failed.";     // 검증 실패
  String DUPLICATE_EMAIL = "Duplicate email.";      // 이메일 중복
  String DUPLICATE_NICKNAME = "Duplicate tel number.";   // 닉네임 중복
  String DUPLICATE_TEL_NUMBER= "Duplicate nicekname.";  // 휴대폰 중복  
  String NOT_EXISTED_USER = "This user does not exist.";     // 존재하지않는 유저    
  String NOT_EXISTED_BOARD = "This board does not exist.";    // 존재하지않는 글  
  String SIGN_IN_FAIL = "Login information mismatch.";         // 로그인 실패 
  String AUTHORIZATION_FAIL = "Authorization Failed.";   // 인증실패
  String NO_PERMISSION = "Do not have permission.";        // 권한 없음
  String DATABASE_ERROR = "Database error.";      // 데이터베이스 에러
}

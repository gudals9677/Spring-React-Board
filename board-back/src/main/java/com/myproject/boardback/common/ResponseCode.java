package com.myproject.boardback.common;

public interface ResponseCode {

  String SUCCESS = "SU";              // 성공
  String VALIDATION_FAILED= "VF";     // 검증 실패
  String DUPLICATE_EMAIL = "DE";      // 이메일 중복
  String DUPLICATE_NICKNAME = "DN";   // 닉네임 중복
  String DUPLICATE_TEL_NUMBER= "DT";  // 휴대폰 중복  
  String NOT_EXISTED_USER = "NU";     // 존재하지않는 유저    
  String NOT_EXISTED_BOARD = "NB";    // 존재하지않는 글  
  String SIGN_IN_FAIL = "SF";         // 로그인 실패 
  String AUTHORIZATION_FAIL = "AF";   // 인증실패
  String NO_PERMISSION = "NP";        // 권한 없음
  String DATABASE_ERROR = "DBE";      // 데이터베이스 에러
}

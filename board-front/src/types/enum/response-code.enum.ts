enum ResponseCode {
  
  SUCCESS = "SU" ,            // 성공
  VALIDATION_FAILED= "VF"  , // 검증 실패
  DUPLICATE_EMAIL = "DE"  ,    // 이메일 중복
  DUPLICATE_NICKNAME = "DN" ,  // 닉네임 중복
  DUPLICATE_TEL_NUMBER= "DT" , // 휴대폰 중복  
  NOT_EXISTED_USER = "NU" ,    // 존재하지않는 유저    
  NOT_EXISTED_BOARD = "NB" ,   // 존재하지않는 글  
  SIGN_IN_FAIL = "SF"     ,    // 로그인 실패 
  AUTHORIZATION_FAIL = "AF" ,  // 인증실패
  NO_PERMISSION = "NP"     ,   // 권한 없음
  DATABASE_ERROR = "DBE" ,    // 데이터베이스 에러
}

export default ResponseCode;
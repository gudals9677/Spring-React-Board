package com.myproject.boardback.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.security.Key;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
  
  @Value("${secret-key}")
  private String secretKey;

  public String create(String email) {
    // 토큰 만료날짜 : 현재 시간에서 + 1시간
    Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
    Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    String jwt = Jwts.builder()
            .signWith(key, SignatureAlgorithm.HS256)
            .setSubject(email).setIssuedAt(new Date()).setExpiration(expiredDate)
            .compact();

    return jwt;
}

  // JWT 검증
  public String validate(String jwt){

    Claims claims = null;
    Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));


    try{
      claims = Jwts.parserBuilder()
      .setSigningKey(key)
      .build()
      .parseClaimsJws(jwt)
      .getBody();
    }catch(Exception e){
      e.printStackTrace();
      return null;
    }
    return claims.getSubject();
  }
}

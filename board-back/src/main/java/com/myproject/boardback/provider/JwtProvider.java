package com.myproject.boardback.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.nio.charset.StandardCharsets;
import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
  
  @Value("${secret-key}")
  private String secretKey;

  public String create(String email) {
    // 현재 시간
    Instant now = Instant.now();
    Date issuedAt = Date.from(now);
    // 토큰 만료날짜 : 현재 시간에서 + 1시간
    Date expiredDate = Date.from(now.plus(1, ChronoUnit.HOURS));
    Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    System.out.println("Issued At: " + issuedAt);
    System.out.println("Expires At: " + expiredDate);

    String jwt = Jwts.builder()
            .signWith(key, SignatureAlgorithm.HS256)
            .setSubject(email)
            .setIssuedAt(issuedAt)
            .setExpiration(expiredDate)
            .compact();

    System.out.println("JWT: " + jwt);
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

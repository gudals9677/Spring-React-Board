package com.myproject.boardback.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
  
  @Value("${secret-key}")
  private String secretKey;

  public String create(String email, String secretKey) {
    // 토큰 만료날짜 : 현재 시간에서 + 1시간
    Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
    byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
    SecretKey key = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());

    String jwt = Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, key.getEncoded())
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(expiredDate)
            .compact();

    return jwt;
}

  // JWT 검증
  public String validate(String jwt){

    Claims claims = null;
    byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
    SecretKey key = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());

    try{
      claims = Jwts.parser().setSigningKey(key)
      .parseClaimsJws(jwt)
      .getBody();
    }catch(Exception e){
      e.printStackTrace();
      return null;
    }
    return claims.getSubject();
  }
}

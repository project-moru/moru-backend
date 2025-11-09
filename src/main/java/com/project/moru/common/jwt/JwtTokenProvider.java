package com.project.moru.common.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenProvider {
  
  /** 하드코딩 부분 수정 필요! **/
  private final long accessTokenValidity = 3600000;
  private final long refreshTokenValidity = 25200000;
  private final SecretKey secretKey = Keys.hmacShaKeyFor("my-secret-key-my-secret-key-my-secret-key-123456".getBytes());
  
  /** Access-Token 생성 **/
  public String generateAccessToken(String username) {
    return buildToken(username, accessTokenValidity);
  }
  
  /** Refresh-Token 생성 */
  public String generateRefreshToken(String username) {
    return buildToken(username, refreshTokenValidity);
  }
  
//  public String getUsername(String token) {
//    return parseClaims(token).getSubject();
//  }

  public String getUsername(String token) {
    try {
      // 1. 토큰 파싱
      Claims claims = Jwts.parserBuilder()
              .setSigningKey(secretKey) // JWT 서명 키
              .build()
              .parseClaimsJws(token)
              .getBody();

      return claims.get("username", String.class);

    } catch (Exception e) {
      return null;
    }
  }
  
  /** Token 생성 */
  private String buildToken(String username, long validity) {
    Date now = new Date();
    Date expiry = new Date(now.getTime() + validity);
    
    return Jwts.builder()
        .claim("username",username)
//        .setSubject(username)
        .setIssuedAt(now)
        .setExpiration(expiry)
        .signWith(secretKey)
        .compact();
  }
  
  /** Authorization 헤더에서 accessToken 추출 **/
  public String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7); // "Bearer " 이후 토큰 부분만 반환
    }
    return null;
  }
  
  /** 토큰 유효성 검사 */
  public boolean validateToken(String token) {
    try {
      parseClaims(token);
      return true;
    } catch (ExpiredJwtException e) {
      throw new JwtException("만료된 토큰입니다.", e);
    } catch (JwtException e) {
      throw new JwtException("유효하지 않은 토큰입니다.", e);
    } catch (IllegalArgumentException e) {
      throw new JwtException("잘못된 토큰 형식입니다.", e);
    }
  }
  
  /**
   * claims 파싱
   * 서명 / 만료시간 검증 **/
  private Claims parseClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(secretKey)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }
}

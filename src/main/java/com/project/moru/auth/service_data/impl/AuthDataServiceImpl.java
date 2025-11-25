package com.project.moru.auth.service_data.impl;

import com.project.moru.auth.service_data.AuthDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthDataServiceImpl implements AuthDataService {
  private final StringRedisTemplate redisTemplate;
  private static final String KEY_PREFIX = "auth:refresh:";
  
  @Override
  public void saveRefreshToken(Long userId, String token, long duration, TimeUnit unit) {
    redisTemplate
        .opsForValue()
        .set(
            KEY_PREFIX + userId,
            token, duration, unit
        );
  }
  
  @Override
  public String findRefreshToken(Long userId) {
    return redisTemplate
        .opsForValue()
        .get(KEY_PREFIX + userId);
  }
  
  @Override
  public void deleteRefreshToken(Long userId) {
    redisTemplate.delete(KEY_PREFIX + userId);
  }
}

package com.project.moru.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthDataService {
  private final StringRedisTemplate redisTemplate;
  private static final String KEY_PREFIX = "auth:refresh:";
  
  public void save(Long userId, String token, long duration, TimeUnit unit) {
    redisTemplate
        .opsForValue()
        .set(
            KEY_PREFIX + userId,
            token, duration, unit
        );
  }
  
  public String find(Long userId) {
    return redisTemplate
        .opsForValue()
        .get(KEY_PREFIX + userId);
  }
  
  public void delete(Long userId) {
    redisTemplate.delete(KEY_PREFIX + userId);
  }
}

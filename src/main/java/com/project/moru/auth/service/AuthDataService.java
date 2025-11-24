package com.project.moru.auth.service;

import java.util.concurrent.TimeUnit;

public interface AuthDataService {
  void saveRefreshToken(Long userId, String token, long duration, TimeUnit unit);
  String findRefreshToken(Long userId);
  void deleteRefreshToken(Long userId);
}

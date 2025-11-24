package com.project.moru.auth.domain.dto;

import lombok.*;

import java.util.concurrent.TimeUnit;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RefreshTokenDto {
  
  private Long userId;
  private String refreshToken;
  private long duration;
  private TimeUnit unit;
}

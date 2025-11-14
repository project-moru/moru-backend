package com.project.moru.domain.dto.user;

import com.project.moru.common.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserResponseDto {
  private final Long userId;
  private final String username;
  private final String password;
  private final String nickname;
  private final Job job;
}

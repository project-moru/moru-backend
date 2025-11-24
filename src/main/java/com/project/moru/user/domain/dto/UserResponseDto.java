package com.project.moru.user.domain.dto;

import com.project.moru.user.constant.Job;
import com.project.moru.user.constant.Use;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserResponseDto {
  private final String username;
  private final String nickname;
  private final Job job;
  private final Use useYn;
}

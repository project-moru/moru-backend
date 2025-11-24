package com.project.moru.domain.dto.auth;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "accessToken")
public class LoginResponseDto {
  private String username;
  private String nickname;
  private String accessToken;
}

package com.project.moru.domain.dto.auth;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"accessToken", "refreshToken"})
public class LoginResultDto {
  
  private String username;
  private String nickname;
  private String accessToken;
  private String refreshToken;
  
  public LoginResponseDto toResponseDto() {
    return LoginResponseDto.builder()
        .username(this.username)
        .nickname(this.nickname)
        .accessToken(this.accessToken)
        .build();
  }
}

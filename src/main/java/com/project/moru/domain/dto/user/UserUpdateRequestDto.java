package com.project.moru.domain.dto.user;

import com.project.moru.common.constant.Job;
import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserUpdateRequestDto {
  
  @Size(min = 8, message = "아이디는 최소 8자 이상이어야 합니다.")
  private String username;
  
  @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
  @Pattern(
      regexp = ".*[!@#$%^&*(),.?\":{}|<>].*",
      message = "비밀번호에는 최소 1개의 특수문자가 포함되어야 합니다."
  )
  private String password;
  
  private String nickname;
  
  private Job job;
}
package com.project.moru.user.domain.dto;

import com.project.moru.common.constant.Job;
import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserUpdateRequestDto implements UserValidatable {
  
  @Size(min = 5, message = "아이디는 최소 5자 이상이어야 합니다.")
  private String username;
  
  private String name;
  
  private String nickname;
  
  private Job job;
}
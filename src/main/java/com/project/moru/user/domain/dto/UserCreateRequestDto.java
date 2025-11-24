package com.project.moru.user.domain.dto;

import com.project.moru.user.constant.Job;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCreateRequestDto implements UserValidatable, PasswordChange {
  
  @Schema(description = "회원 아이디", example = "test01")
  @NotBlank(message = "아이디는 필수입니다.")
  @Size(min = 5, message = "아이디는 최소 5자 이상이어야 합니다.")
  private String username;
  
  @Schema(description = "비밀번호", example = "test01!12")
  @NotBlank(message = "비밀번호는 필수입니다.")
  @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
  @Pattern(
      regexp = ".*[!@#$%^&*(),.?\":{}|<>].*",
      message = "비밀번호에는 최소 1개의 특수문자가 포함되어야 합니다."
  )
  private String password;
  
  @Schema(description = "별명", example = "테스터1")
  @NotBlank(message = "별명은 필수입니다.")
  private String nickname;
  
  @Schema(
      description = "직업", example = "DESIGNER",
      allowableValues = {"DESIGNER", "RESEARCHER"}
  )
  private Job job;
}
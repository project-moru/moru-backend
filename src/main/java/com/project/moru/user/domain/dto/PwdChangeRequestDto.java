package com.project.moru.user.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PwdChangeRequestDto implements ChangePwd {
  
  @NotBlank
  @Schema(description = "기존 비밀번호", example = "test01!12")
  private String beforePassword;
  
  @NotBlank
  @Schema(description = "새로운 비밀번호", example = "test!1234")
  @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
  @Pattern(
      regexp = ".*[!@#$%^&*(),.?\":{}|<>].*",
      message = "비밀번호에는 최소 1개의 특수문자가 포함되어야 합니다."
  )
  private String updatePassword;
}

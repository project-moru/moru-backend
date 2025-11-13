package com.project.moru.domain.dto.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshTokenResponseDto {
    private String accessToken;
    private String refreshToken;
}

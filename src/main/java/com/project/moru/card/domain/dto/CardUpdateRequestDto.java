package com.project.moru.card.domain.dto;

import com.project.moru.common.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardUpdateRequestDto {

    // 수정할 필드만 정의 (제목, 내용, 공개 여부, 이미지)
    @Size(max = 100, message = "카드 제목은 100자를 초과할 수 없습니다.")
    private String cardName;

    @Size(max = 5000, message = "카드 내용은 5000자를 초과할 수 없습니다.")
    private String cardContent;

    private Status isPublic;

    private String imageUrl;

}

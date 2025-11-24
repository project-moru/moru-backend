package com.project.moru.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    PUBLIC("전체공개"),
    FRIENDS("친구공개"),
    PRIVATE("비공개");

    private final String description;
}

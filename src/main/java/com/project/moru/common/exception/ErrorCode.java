package com.project.moru.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // 400 Bad Request (잘못된 요청)
    DUPLICATE_USERNAME(400, "이미 존재하는 아이디입니다."),
    DUPLICATE_NICKNAME(400, "이미 존재하는 닉네임입니다."),
    NOT_VALID_FORMAT(400, "유효하지 않은 이메일 형식입니다."),
    NOT_FOUND_DECK(400, "덱을 찾을 수 없습니다"),
    NOT_FOUND_USER(400,"유저를 찾을 수 없습니다"),
    NOT_FOUND_CARD(400,"카드를 찾을 수 없습니다"),
    NOT_FOUND_DATA_FIELD(400,"카드를 찾을 수 없습니다"),
    INVALID_FILE_URL(400, "유효하지 않은 파일주소 입니다" ),
    NOT_UPLOAD_IMAGE(400,"이미지 업로드에 실패하였습니다" ),
    FAILED_DELETE_IMAGE(400,"이미지 삭제에 실패하였습니다." ),


    NOT_EXIST_CARD(400,"존재하지 않는 카드가 있습니다"),

    // 403 Forbidden (권한 없음)
    ACCESS_DENIED(403,"접근 권한이 없습니다." );

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}

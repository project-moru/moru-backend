package com.project.moru.aws.controller;


import com.project.moru.common.utils.S3Utils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "image", description = "이미지 API")
@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/s3")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Utils s3Utils;

    @PostMapping(value = "", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImg(
            @RequestPart("multipartFile") MultipartFile cardImage
    ) {
        return s3Utils.uploadFile("cards", cardImage);
    }
}

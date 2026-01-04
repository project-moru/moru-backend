package com.project.moru.common.utils;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.project.moru.common.exception.ErrorCode;
import com.project.moru.common.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Utils {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadFile(String dirName, MultipartFile file) {
        try {
            String fileName = createFileName(getExtension(file.getOriginalFilename()));
            String fileUrl = dirName + "/" + fileName;

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            amazonS3Client.putObject(
                    new PutObjectRequest(bucket, fileUrl, file.getInputStream(), metadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)  // 누구나 파일을 읽을 수 있게 설정
            );

            return amazonS3Client.getUrl(bucket, fileUrl).toString();

        } catch (IOException e) {
            throw new GeneralException(ErrorCode.NOT_UPLOAD_IMAGE);
        }
    }
    private String createFileName(String ext) {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
                + "-"
                + UUID.randomUUID().toString()
                + ext;
    }
//
    private String getExtension(String fileName) {
        if (fileName == null) return "";
        int lastIndex = fileName.lastIndexOf(".");
        if (lastIndex == -1) return "";
        return fileName.substring(lastIndex);
    }


    private String extractKeyFromUrl(String fileUrl) {
        try {

            URL url = new URL(fileUrl);
            String path = url.getPath();
            if (path.startsWith("/")) {
                path = path.substring(1);
            }
            return path;
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.INVALID_FILE_URL);
        }
    }

    public void deleteFile(String fileUrl) {
        try {
            String key = extractKeyFromUrl(fileUrl);
            amazonS3Client.deleteObject(bucket, key);
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.FAILED_DELETE_IMAGE);
        }
    }
}

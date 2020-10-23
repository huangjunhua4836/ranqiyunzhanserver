package com.yl.soft.common.util;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import io.minio.policy.PolicyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by hjd on 2020-09-22 14:28
 * --> minio工具类
 **/
@Slf4j
@Component
public class MinioUtil {

    private static String MINIO_URL;
    private static String UPLOADURL;
    private static String BUCKET_NAME;
    private static String ACCESS_KEY;
    private static String SECRET_KEY;

    @Value("${minio.miniourl}")
    public void setMinioUrl(String minioUrl) {
        MINIO_URL = minioUrl;
    }

    @Value("${minio.uploadurl}")
    public void setUploadurl(String uploadurl) {
        UPLOADURL = uploadurl;
    }

    @Value("${minio.bucketName}")
    public void setBucketName(String bucketName) {
        BUCKET_NAME = bucketName;
    }

    @Value("${minio.accessKey}")
    public void setAccessKey(String accessKey) {
        ACCESS_KEY = accessKey;
    }

    @Value("${minio.secretKey}")
    public void setSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }

    private static MinioClient minioClient = null;

    private static MinioClient initMinio(String minioUrl, String accessKey, String secretKey) {
        if (minioClient == null) {
            try {
                minioClient = new MinioClient(minioUrl, accessKey, secretKey);
            } catch (InvalidEndpointException e) {
                e.printStackTrace();
            } catch (InvalidPortException e) {
                e.printStackTrace();
            }
        }
        return minioClient;
    }

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    public static String upload(MultipartFile file,String objectName) {
        try {
            //创建一个MinIO的Java客户端
            initMinio(UPLOADURL, ACCESS_KEY, SECRET_KEY);
            boolean isExist = minioClient.bucketExists(BUCKET_NAME);
            if (isExist) {
                log.debug("存储桶已经存在！");
            } else {
                //创建存储桶并设置只读权限
                minioClient.makeBucket(BUCKET_NAME);
                minioClient.setBucketPolicy(BUCKET_NAME, "*.*", PolicyType.READ_ONLY);
            }
            // 使用putObject上传一个文件到存储桶中
            minioClient.putObject(BUCKET_NAME, objectName, file.getInputStream(), file.getContentType());
            log.info("文件上传成功!");
            return MINIO_URL + "/" + BUCKET_NAME + "/" + objectName;
        } catch (Exception e) {
            log.info("上传发生错误: {}！", e.getMessage());
        }
        return null;
    }

    /**
     * 文件删除
     *
     * @param objectName
     * @return
     */
    public static Boolean delete(String objectName) {
        try {
            initMinio(MINIO_URL, ACCESS_KEY, SECRET_KEY);
            minioClient.removeObject(BUCKET_NAME, objectName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}


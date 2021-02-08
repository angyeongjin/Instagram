package com.kbm.instagram.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;
import javax.annotation.PostConstruct;


@Service
public class S3UploadService {

    private AmazonS3 s3Client = null;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey = null;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey= null;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket = null;

    @Value("${cloud.aws.region.static}")
    private String region = null;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public String uploadFile(MultipartFile file, String dirName) throws IOException {
        String fileName = dirName + UUID.randomUUID() + getExtension(file);
        s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return s3Client.getUrl(bucket, fileName).toString();
    }

    public String getExtension(MultipartFile file) {
        int pos = file.getOriginalFilename().lastIndexOf( "." );
        String ext = "." + file.getOriginalFilename().substring( pos + 1 );
        return ext;
    }
}
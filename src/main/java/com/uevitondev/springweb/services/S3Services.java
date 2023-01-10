package com.uevitondev.springweb.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Services {

    private Logger LOG = LoggerFactory.getLogger(S3Services.class);


    @Value("${s3.bucket}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public void uploadFile(String localFilePath) {
        try {
            File file = new File(localFilePath);
            LOG.info("Iniciado Upload!");
            s3Client.putObject(new PutObjectRequest(bucketName, "teste.png", file));
            LOG.info("Upload Finalizado!");
        } catch (AmazonServiceException amazonServiceException) {
            LOG.info("AmazonServiceException" + amazonServiceException.getErrorMessage());
            LOG.info("Status code: " + amazonServiceException.getErrorCode());
        } catch (AmazonClientException amazonClientException) {
            LOG.info("AmazonClientException " + amazonClientException.getMessage());
        }

    }
}

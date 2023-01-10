package com.uevitondev.springweb.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3Services {

    private Logger LOG = LoggerFactory.getLogger(S3Services.class);


    @Value("${s3.bucket}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public URI uploadFile(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream inputStream = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();

            return uploadFile(inputStream, fileName, contentType);
        } catch (IOException e) {
            throw new RuntimeException("Erro de  IO: " + e.getMessage());
        }

    }

    public URI uploadFile(InputStream inputStream, String fileName, String contentType) {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(contentType);
            LOG.info("Iniciado Upload!");
            s3Client.putObject(bucketName, fileName, inputStream, objectMetadata);
            LOG.info("Upload Finalizado!");

            return s3Client.getUrl(bucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Error ao converter URL pra URI!");
        }
    }
}


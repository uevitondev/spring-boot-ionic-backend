package com.uevitondev.springweb;

import com.uevitondev.springweb.services.S3Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebApplication implements CommandLineRunner {

    @Autowired
    private S3Services s3Services;

    public static void main(String[] args) {
        SpringApplication.run(SpringWebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        s3Services.uploadFile("C:\\temp\\fotos\\4.png");
    }
}

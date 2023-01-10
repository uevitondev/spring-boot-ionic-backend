package com.uevitondev.springweb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}

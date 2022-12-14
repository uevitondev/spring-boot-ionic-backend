package com.uevitondev.springweb.config;

import com.uevitondev.springweb.services.DBService;
import com.uevitondev.springweb.services.EmailService;
import com.uevitondev.springweb.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instatianteDatabase() throws ParseException {
        dbService.instatiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }
}

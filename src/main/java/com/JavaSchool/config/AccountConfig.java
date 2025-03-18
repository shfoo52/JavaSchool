package com.JavaSchool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.JavaSchool.model.Account;

@Configuration
public class AccountConfig {
    @Bean
    public Account account() {
        return new Account();
    }
}
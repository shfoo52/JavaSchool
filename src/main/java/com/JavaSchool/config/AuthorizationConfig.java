package com.JavaSchool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.JavaSchool.model.Authorization;

@Configuration
public class AuthorizationConfig {
    @Bean
    public Authorization authorization() {
        return new Authorization();
    }
}
package com.JavaSchool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.JavaSchool.model.SRLog;

@Configuration
public class SRLogConfig {
    @Bean
    public SRLog srLog() {
        return new SRLog();
    }
}

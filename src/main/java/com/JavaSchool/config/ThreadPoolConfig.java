package com.JavaSchool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.*;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ExecutorService commThreadPool() {
        return Executors.newFixedThreadPool(10); // 10 tasks
    }

    @Bean
    public ExecutorService subrouterThreadPool() {
        return Executors.newFixedThreadPool(6); // 6 tasks (3 for request, 3 for response)
    }
    
    @Bean
    public ExecutorService authorizationThreadPool() {
        return Executors.newFixedThreadPool(3); // 3 tasks
    }
        
}
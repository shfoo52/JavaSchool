package com.JavaSchool.config;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.JavaSchool.model.InternalMessage;

@Configuration
public class BlockingQueueConfig {

    @Bean
    public BlockingQueue<InternalMessage> messageQueue() {
        return new LinkedBlockingQueue<>();
    }
    
    @Bean
    public BlockingQueue<InternalMessage> authorizationQueue() {
        return new LinkedBlockingQueue<>();
    }
    
    @Bean
    public BlockingQueue<InternalMessage> authorizationResponseQueue() {
        return new LinkedBlockingQueue<>();
    }
    
    @Bean
    public BlockingQueue<InternalMessage> subRouterResponseQueue() {
        return new LinkedBlockingQueue<>();
    }
    
}
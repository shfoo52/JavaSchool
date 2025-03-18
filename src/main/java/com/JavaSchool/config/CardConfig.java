package com.JavaSchool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.JavaSchool.model.Card;

@Configuration
public class CardConfig {
    @Bean
    public Card card() {
        return new Card();
    }
}

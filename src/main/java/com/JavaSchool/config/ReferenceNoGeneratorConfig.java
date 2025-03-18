package com.JavaSchool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import util.ReferenceNoGenerator;
import com.JavaSchool.dao.SeqNumDAO;

@Configuration
public class ReferenceNoGeneratorConfig {

    @Bean  // ✅ Register ReferenceNoGenerator as a Bean with dependency injection
    public ReferenceNoGenerator referenceNoGenerator(SeqNumDAO seqNumDAO) {
        return new ReferenceNoGenerator(seqNumDAO);
    }
}
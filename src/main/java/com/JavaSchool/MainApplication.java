package com.JavaSchool;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.vaadin.flow.spring.annotation.EnableVaadin;

@SpringBootApplication(scanBasePackages = "com.JavaSchool")
@EnableVaadin
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
        System.out.println("* * * * * *   Credit Card Authorization Service Started   * * * * *");
    }
    
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
		//	System.out.println("==== Beans provided by Spring Boot ====");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
		//		System.out.println(beanName);
			}
		};
	}
}
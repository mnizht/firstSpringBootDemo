package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 启动入口*/
@SpringBootApplication
@Configuration
public class FirstSpringBootDemoApplication implements WebMvcConfigurer {



	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

		configurer.favorPathExtension(false);

	}


	public static void main(String[] args) {
		SpringApplication.run(FirstSpringBootDemoApplication.class, args);
	}
}

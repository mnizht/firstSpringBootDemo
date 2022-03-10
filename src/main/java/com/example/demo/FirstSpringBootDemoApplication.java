package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 启动入口*/
@SpringBootApplication
@MapperScan("com.example.demo.sys.mapper")
public class FirstSpringBootDemoApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringBootDemoApplication.class, args);
	}
}

package com.sist.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = {"com.sist.web.controller"})
@SpringBootApplication
public class SpringBootJpaThymeleafProject2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaThymeleafProject2Application.class, args);
	}

}

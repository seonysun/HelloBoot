package com.sist.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
@ComponentScan(basePackages = {"com.sist.web.controller","com.sist.web.dao","com.sist.web.entity",
		"com.sist.web.news"})
@EnableAspectJAutoProxy
@SpringBootApplication
public class BootReactProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootReactProjectApplication.class, args);
	}

}

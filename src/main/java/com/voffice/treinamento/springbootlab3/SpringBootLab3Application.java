package com.voffice.treinamento.springbootlab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBootLab3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLab3Application.class, args);
	}
}

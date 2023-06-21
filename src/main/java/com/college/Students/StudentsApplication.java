package com.college.Students;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.Banner;


@EnableAutoConfiguration
@SpringBootApplication
public class StudentsApplication {

	public static void main(String[] args) {
//		SpringApplication.run(StudentsApplication.class, args);
		SpringApplication app = new SpringApplication(StudentsApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
		System.out.println("Project Ran....");
	}

}

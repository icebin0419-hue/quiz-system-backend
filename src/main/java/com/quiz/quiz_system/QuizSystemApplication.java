package com.quiz.quiz_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.security.autoconfigure.web.servlet.ServletWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(exclude = {
	UserDetailsServiceAutoConfiguration.class, //
	ServletWebSecurityAutoConfiguration.class
})

public class QuizSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizSystemApplication.class, args);
	}
}
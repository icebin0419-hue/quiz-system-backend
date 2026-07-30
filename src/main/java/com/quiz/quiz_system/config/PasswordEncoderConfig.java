package com.quiz.quiz_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 提供 PasswordEncoder Bean，讓 UserServiceImpl 可以 @Autowired 注入使用
 *
 * 注意：這裡只單純用 BCrypt 做「密碼加密/比對」，
 * 並沒有引入完整的 Spring Security 登入驗證機制(如 Session、JWT、攔截器擋 API)。
 * 如果之後要做「登入後才能呼叫某些 API」的權限控管，需要另外設定 SecurityFilterChain。
 *
 * pom.xml 需要加入依賴：
 * <dependency>
 *     <groupId>org.springframework.security</groupId>
 *     <artifactId>spring-security-crypto</artifactId>
 * </dependency>
 * (若已引入 spring-boot-starter-security 則不需要另外加)
 */
@Configuration
public class PasswordEncoderConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
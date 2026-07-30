package com.quiz.quiz_system.dto;

import com.quiz.quiz_system.common.ValidationMsg;
import jakarta.validation.constraints.NotBlank;

/**
 * 登入使用的請求 DTO
 * 用 phone 當作登入帳號
 */
public class UserLoginRequest {

	@NotBlank(message = ValidationMsg.USER_PHONE_REQUIRED)
	private String phone;

	@NotBlank(message = ValidationMsg.USER_PASSWORD_REQUIRED)
	private String password;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
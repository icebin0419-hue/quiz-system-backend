package com.quiz.quiz_system.dto;

import com.quiz.quiz_system.common.ValidationMsg;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 註冊使用的請求 DTO
 * 對應 Entity 實際欄位：name、phone、password、email、age
 */
public class UserRegisterRequest {

	@NotBlank(message = ValidationMsg.USER_NAME_REQUIRED)
	@Size(max = 50)
	private String name;

	@NotBlank(message = ValidationMsg.USER_PHONE_REQUIRED)
	@Pattern(regexp = "^09\\d{8}$", message = ValidationMsg.USER_PHONE_INVALID)
	private String phone;

	@NotBlank(message = ValidationMsg.USER_PASSWORD_REQUIRED)
	@Size(min = 6, max = 100, message = ValidationMsg.USER_PASSWORD_SIZE)
	private String password;

	@NotBlank(message = ValidationMsg.USER_EMAIL_REQUIRED)
	@Email(message = ValidationMsg.USER_EMAIL_INVALID)
	private String email;

	private Integer age;

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.phone = phone; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public Integer getAge() { return age; }
	public void setAge(Integer age) { this.age = age; }
}
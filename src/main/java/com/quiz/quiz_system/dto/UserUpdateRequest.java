package com.quiz.quiz_system.dto;

import com.quiz.quiz_system.common.ValidationMsg;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 更新使用者資料的請求 DTO
 * password 為選填：若前端沒傳(null 或空字串)，Service 層就不會更動密碼
 * phone 不開放修改，因為它是登入帳號(唯一值)，如需換手機號碼建議走另外的驗證流程
 */
public class UserUpdateRequest {

	@NotBlank(message = ValidationMsg.USER_NAME_REQUIRED)
	@Size(max = 50)
	private String name;

	@NotBlank(message = ValidationMsg.USER_EMAIL_REQUIRED)
	@Email(message = ValidationMsg.USER_EMAIL_INVALID)
	private String email;

	private Integer age;

	@Size(min = 6, max = 100, message = ValidationMsg.USER_PASSWORD_SIZE)
	private String password; // 選填，不加 @NotBlank

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
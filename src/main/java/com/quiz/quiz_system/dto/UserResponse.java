package com.quiz.quiz_system.dto;

import java.time.LocalDateTime;

/**
 * 回傳給前端的使用者資料
 * 注意：絕對不能把 password 放進這個 DTO，即使是加密後的也不行
 */
public class UserResponse {

	private Long id;
	private String name;
	private String phone;
	private String email;
	private Integer age;
	private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
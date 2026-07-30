package com.quiz.quiz_system.service;

import com.quiz.quiz_system.dto.UserRegisterRequest;
import com.quiz.quiz_system.dto.UserLoginRequest;
import com.quiz.quiz_system.dto.UserResponse;
import com.quiz.quiz_system.dto.UserUpdateRequest;
import java.util.List;

/**
 * User 商業邏輯層介面
 */
public interface UserService {

	// 1. 註冊
	UserResponse register(UserRegisterRequest request);

	// 2. 登入
	UserResponse login(UserLoginRequest request);

	// 3. 取得所有使用者
	List<UserResponse> getAllUsers();

	// 4. 依 id 取得單一使用者
	UserResponse getUserById(Long id);

	// 5. 更新使用者資料
	UserResponse updateUser(Long id, UserUpdateRequest request);

	// 6. 刪除使用者
	void deleteUser(Long id);
}
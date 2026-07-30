package com.quiz.quiz_system.controller;

import com.quiz.quiz_system.dto.LoginResponse;
import com.quiz.quiz_system.dto.UserLoginRequest;
import com.quiz.quiz_system.dto.UserRegisterRequest;
import com.quiz.quiz_system.dto.UserResponse;
import com.quiz.quiz_system.dto.UserUpdateRequest;
import com.quiz.quiz_system.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	// 1. 註冊
	@PostMapping("/register")
	public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegisterRequest request) {
		UserResponse response = userService.register(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// 2. 登入
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody UserLoginRequest request) {
	    UserResponse user = userService.login(request);
	    // 暫時產生假 token（之後可換成真正 JWT）
	    String token = "temp-token-" + System.currentTimeMillis();
	    return ResponseEntity.ok(new LoginResponse("Login successful", user, token));
	}

	// 3. 取得所有使用者
	@GetMapping
	public ResponseEntity<List<UserResponse>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	// 4. 依 id 取得單一使用者
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}

	// 5. 更新使用者資料
	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
			@Valid @RequestBody UserUpdateRequest request) {
		return ResponseEntity.ok(userService.updateUser(id, request));
	}

	// 6. 刪除使用者
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
package com.quiz.quiz_system.service;

import com.quiz.quiz_system.common.ValidationMsg;
import com.quiz.quiz_system.dto.UserLoginRequest;
import com.quiz.quiz_system.dto.UserRegisterRequest;
import com.quiz.quiz_system.dto.UserResponse;
import com.quiz.quiz_system.dto.UserUpdateRequest;
import com.quiz.quiz_system.entity.User;
import com.quiz.quiz_system.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder; // BCrypt 加密器，由 PasswordEncoderConfig 提供

	/**
	 * 1. 註冊
	 * 流程：檢查手機/信箱是否重複 -> 加密密碼 -> 存入資料庫
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserResponse register(UserRegisterRequest request) {
		if (userRepository.existsByPhone(request.getPhone())) {
			throw new IllegalArgumentException(ValidationMsg.PHONE_ALREADY_EXISTS);
		}
		if (userRepository.existsByEmail(request.getEmail())) {
			throw new IllegalArgumentException(ValidationMsg.EMAIL_ALREADY_EXISTS);
		}

		User user = new User();
		user.setName(request.getName());
		user.setPhone(request.getPhone());
		user.setEmail(request.getEmail());
		user.setAge(request.getAge());
		// 重要：絕對不能把明文密碼存進資料庫，一定要先經過 encode()
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		User saved = userRepository.save(user);
		return convertToResponse(saved);
	}

	/**
	 * 2. 登入
	 * 流程：依 phone 找出使用者 -> 用 passwordEncoder.matches() 比對明文密碼與加密密碼
	 * 注意：不可以自己把明文密碼加密後再用 equals() 比對，BCrypt 每次加密結果都不同，只能用 matches()
	 */
	@Override
	@Transactional(readOnly = true)
	public UserResponse login(UserLoginRequest request) {
		User user = userRepository.findByPhone(request.getPhone())
				.orElseThrow(() -> new IllegalArgumentException(ValidationMsg.INVALID_CREDENTIALS));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new IllegalArgumentException(ValidationMsg.INVALID_CREDENTIALS);
		}

		return convertToResponse(user);
	}

	/**
	 * 3. 取得所有使用者
	 */
	@Override
	@Transactional(readOnly = true)
	public List<UserResponse> getAllUsers() {
		return userRepository.findAll().stream()
				.map(this::convertToResponse)
				.collect(Collectors.toList());
	}

	/**
	 * 4. 依 id 取得單一使用者
	 */
	@Override
	@Transactional(readOnly = true)
	public UserResponse getUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(ValidationMsg.USER_NOT_FOUND));
		return convertToResponse(user);
	}

	/**
	 * 5. 更新使用者資料
	 * name/email/age 一定會更新；password 只有在前端有傳值時才會更新(選填)
	 * phone 不開放修改
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserResponse updateUser(Long id, UserUpdateRequest request) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(ValidationMsg.USER_NOT_FOUND));

		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setAge(request.getAge());
		if (request.getPassword() != null && !request.getPassword().isBlank()) {
			user.setPassword(passwordEncoder.encode(request.getPassword()));
		}

		User updated = userRepository.save(user);
		return convertToResponse(updated);
	}

	/**
	 * 6. 刪除使用者
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteUser(Long id) {
		if (!userRepository.existsById(id)) {
			throw new IllegalArgumentException(ValidationMsg.USER_NOT_FOUND);
		}
		userRepository.deleteById(id);
	}

	// ==================== 私有輔助方法 ====================
	private UserResponse convertToResponse(User user) {
		UserResponse dto = new UserResponse();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setPhone(user.getPhone());
		dto.setEmail(user.getEmail());
		dto.setAge(user.getAge());
		dto.setCreatedAt(user.getCreatedAt());
		return dto;
	}
}
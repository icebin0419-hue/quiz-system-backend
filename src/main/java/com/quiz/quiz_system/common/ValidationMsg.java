package com.quiz.quiz_system.common;

/**
 * 驗證錯誤訊息統一管理
 */
public class ValidationMsg {

	// ==================== User Validation ====================
	public static final String USER_NAME_REQUIRED = "Name cannot be blank!!";

	public static final String USER_PHONE_REQUIRED = "Phone cannot be blank!!";

	public static final String USER_PHONE_INVALID = "Phone format is invalid!!";

	public static final String USER_PASSWORD_REQUIRED = "Password cannot be blank!!";

	public static final String USER_PASSWORD_SIZE = "Password must be at least 6 characters!!";

	public static final String USER_EMAIL_REQUIRED = "Email cannot be blank!!";

	public static final String USER_EMAIL_INVALID = "Email format is invalid!!";

	// ==================== User Business Rule Messages ====================
	public static final String PHONE_ALREADY_EXISTS = "Phone already exists!!";

	public static final String EMAIL_ALREADY_EXISTS = "Email already exists!!";

	public static final String USER_NOT_FOUND = "User not found!!";

	public static final String INVALID_CREDENTIALS = "Phone or password is incorrect!!";
}
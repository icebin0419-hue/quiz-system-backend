package com.quiz.quiz_system.exception;

import com.quiz.quiz_system.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全域例外處理器
 * 用 @RestControllerAdvice 集中攔截所有 Controller 拋出的例外，
 * 統一轉換成 ApiResponse 格式回傳，避免每個 Controller 都要寫 try-catch。
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	// 攔截 @Valid 驗證失敗(例如 @NotBlank、@Email 沒通過)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException ex) {
		String message = ex.getBindingResult().getFieldErrors().stream()
				.findFirst()
				.map(error -> error.getDefaultMessage())
				.orElse("Validation failed");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, message));
	}

	// 攔截業務邏輯錯誤(例如帳號重複、找不到使用者、登入失敗)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, ex.getMessage()));
	}

	// 攔截其他未預期的例外，避免直接把 StackTrace 洩漏給前端
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> handleGeneralException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiResponse(false, "Internal server error: " + ex.getMessage()));
	}
}
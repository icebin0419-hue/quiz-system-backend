package com.quiz.quiz_system.dto;

/**
 * 登入成功後的回傳內容
 */
public class LoginResponse {

    private String message;
    private UserResponse user;
    private String token;   // 新增 token

    public LoginResponse() {
    }

    public LoginResponse(String message, UserResponse user, String token) {
        this.message = message;
        this.user = user;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
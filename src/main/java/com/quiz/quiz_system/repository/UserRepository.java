package com.quiz.quiz_system.repository;

import com.quiz.quiz_system.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User 的資料存取層
 * 使用 phone 當作登入帳號(唯一值)
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// 自動產生：SELECT * FROM users WHERE phone = ?
	Optional<User> findByPhone(String phone);

	// 自動產生：SELECT COUNT(*) > 0 FROM users WHERE phone = ?
	boolean existsByPhone(String phone);

	// 自動產生：SELECT COUNT(*) > 0 FROM users WHERE email = ?
	boolean existsByEmail(String email);
}
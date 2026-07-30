package com.quiz.quiz_system.repository;

import com.quiz.quiz_system.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
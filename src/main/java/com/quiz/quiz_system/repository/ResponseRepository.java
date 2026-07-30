package com.quiz.quiz_system.repository;

import com.quiz.quiz_system.entity.Response;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> findByQuizId(Long quizId);
}
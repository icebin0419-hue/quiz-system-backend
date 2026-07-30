package com.quiz.quiz_system.repository;

import com.quiz.quiz_system.entity.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByQuizIdOrderByQuestionNumAsc(Long quizId);
    void deleteByQuizId(Long quizId);
}
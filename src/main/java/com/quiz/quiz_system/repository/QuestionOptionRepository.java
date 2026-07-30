package com.quiz.quiz_system.repository;

import com.quiz.quiz_system.entity.QuestionOption;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Long> {
    List<QuestionOption> findByQuestionId(Long questionId);
}
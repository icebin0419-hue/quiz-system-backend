package com.quiz.quiz_system.service;

import com.quiz.quiz_system.dto.QuizRequest;
import com.quiz.quiz_system.entity.Quiz;
import java.util.List;

public interface QuizService {
    List<Quiz> getAllQuizzes();
    Quiz getQuizById(Long id);
    Quiz createQuiz(Quiz quiz);
    Quiz createQuizWithQuestions(QuizRequest request); // ← 新增這行
    Quiz updateQuiz(Long id, Quiz quiz);
    void deleteQuiz(Long id);
    Quiz updateQuizWithQuestions(Long id, QuizRequest request);
}

package com.quiz.quiz_system.service;

import com.quiz.quiz_system.entity.Question;
import java.util.List;

public interface QuestionService {
    List<Question> getQuestionsByQuizId(Long quizId);
    Question createQuestion(Long quizId, Question question);
    Question updateQuestion(Long id, Question question);
    void deleteQuestion(Long id);
}
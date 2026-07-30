package com.quiz.quiz_system.service;

import com.quiz.quiz_system.entity.Question;
import com.quiz.quiz_system.entity.Quiz;
import com.quiz.quiz_system.repository.QuestionRepository;
import com.quiz.quiz_system.repository.QuizRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Question> getQuestionsByQuizId(Long quizId) {
        return questionRepository.findByQuizIdOrderByQuestionNumAsc(quizId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Question createQuestion(Long quizId, Question question) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("找不到該問卷"));
        question.setQuiz(quiz);
        return questionRepository.save(question);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Question updateQuestion(Long id, Question question) {
        Question existing = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("找不到該題目"));
        existing.setQuestionNum(question.getQuestionNum());
        existing.setTitle(question.getTitle());
        existing.setType(question.getType());
        existing.setIsRequired(question.getIsRequired());
        return questionRepository.save(existing);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new IllegalArgumentException("找不到該題目");
        }
        questionRepository.deleteById(id);
    }
}
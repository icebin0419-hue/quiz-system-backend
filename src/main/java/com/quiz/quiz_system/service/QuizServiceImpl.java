package com.quiz.quiz_system.service;

import com.quiz.quiz_system.dto.QuizRequest;
import com.quiz.quiz_system.entity.Question;
import com.quiz.quiz_system.entity.QuestionOption;
import com.quiz.quiz_system.entity.Quiz;
import com.quiz.quiz_system.repository.QuestionRepository;
import com.quiz.quiz_system.repository.QuizRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("找不到該問卷"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Quiz updateQuiz(Long id, Quiz quiz) {
        Quiz existing = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("找不到該問卷"));

        existing.setTitle(quiz.getTitle());
        existing.setDescription(quiz.getDescription());
        existing.setStartDate(quiz.getStartDate());
        existing.setEndDate(quiz.getEndDate());
        existing.setIsPublished(quiz.getIsPublished());

        return quizRepository.save(existing);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteQuiz(Long id) {
        if (!quizRepository.existsById(id)) {
            throw new IllegalArgumentException("找不到該問卷");
        }
        quizRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Quiz createQuizWithQuestions(QuizRequest request) {
        Quiz quiz = new Quiz();
        quiz.setTitle(request.getTitle());
        quiz.setDescription(request.getDescription());
        quiz.setIsPublished(request.getIsPublished() != null ? request.getIsPublished() : false);

        List<Question> questions = new ArrayList<>();
        if (request.getQuestions() != null) {
            int qNum = 1;
            for (QuizRequest.QuestionRequest qReq : request.getQuestions()) {
                Question question = new Question();
                question.setQuiz(quiz);
                question.setQuestionNum(qNum++);
                question.setTitle(qReq.getTitle());
                question.setType(qReq.getType());
                question.setIsRequired(qReq.getRequired() != null ? qReq.getRequired() : true);

                List<QuestionOption> options = new ArrayList<>();
                if (qReq.getOptions() != null) {
                    char code = 'A';
                    for (QuizRequest.OptionRequest oReq : qReq.getOptions()) {
                        QuestionOption option = new QuestionOption();
                        option.setQuestion(question);
                        option.setOptionCode(String.valueOf(code++));
                        option.setOptionText(oReq.getLabel());
                        options.add(option);
                    }
                }
                question.setOptions(options);
                questions.add(question);
            }
        }
        quiz.setQuestions(questions);

        return quizRepository.save(quiz);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Quiz updateQuizWithQuestions(Long id, QuizRequest request) {
        Quiz existing = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("找不到該問卷"));

        // 1. 更新 Quiz 本體
        existing.setTitle(request.getTitle());
        existing.setDescription(request.getDescription());
        existing.setIsPublished(request.getIsPublished() != null ? request.getIsPublished() : false);

        // 2. 刪除舊有題目（連帶選項會因為 cascade 自動刪除）
        questionRepository.deleteByQuizId(id);
        existing.getQuestions().clear();

        // 3. 重新建立題目與選項
        List<Question> questions = new ArrayList<>();
        if (request.getQuestions() != null) {
            int qNum = 1;
            for (QuizRequest.QuestionRequest qReq : request.getQuestions()) {
                Question question = new Question();
                question.setQuiz(existing);
                question.setQuestionNum(qNum++);
                question.setTitle(qReq.getTitle());
                question.setType(qReq.getType());
                question.setIsRequired(qReq.getRequired() != null ? qReq.getRequired() : true);

                List<QuestionOption> options = new ArrayList<>();
                if (qReq.getOptions() != null) {
                    char code = 'A';
                    for (QuizRequest.OptionRequest oReq : qReq.getOptions()) {
                        QuestionOption option = new QuestionOption();
                        option.setQuestion(question);
                        option.setOptionCode(String.valueOf(code++));
                        option.setOptionText(oReq.getLabel());
                        options.add(option);
                    }
                }
                question.setOptions(options);
                questions.add(question);
            }
        }
        existing.getQuestions().addAll(questions);

        return quizRepository.save(existing);
    }
}
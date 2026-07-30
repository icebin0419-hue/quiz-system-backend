package com.quiz.quiz_system.service;

import com.quiz.quiz_system.dto.ResponseRequest;
import com.quiz.quiz_system.entity.AnswerDetail;
import com.quiz.quiz_system.entity.Quiz;
import com.quiz.quiz_system.entity.Response;
import com.quiz.quiz_system.repository.QuizRepository;
import com.quiz.quiz_system.repository.ResponseRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResponseServiceImpl implements ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response submitResponse(Long quizId, ResponseRequest request) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("找不到該問卷"));

        Response response = new Response();
        response.setQuiz(quiz);
        response.setName(request.getName() != null ? request.getName() : "匿名");
        response.setPhone(request.getPhone() != null ? request.getPhone() : "未提供");
        response.setEmail(request.getEmail());
        response.setAge(request.getAge());

        List<AnswerDetail> answerDetails = new ArrayList<>();
        if (request.getAnswers() != null) {
            for (ResponseRequest.AnswerRequest aReq : request.getAnswers()) {
                AnswerDetail detail = new AnswerDetail();
                detail.setResponse(response);
                detail.setQuestionId(aReq.getQuestionId());
                detail.setAnswer(aReq.getAnswer() != null ? aReq.getAnswer().toString() : "");
                answerDetails.add(detail);
            }
        }
        response.setAnswerDetails(answerDetails);

        return responseRepository.save(response);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Response> getResponsesByQuizId(Long quizId) {
        return responseRepository.findByQuizId(quizId);
    }
}
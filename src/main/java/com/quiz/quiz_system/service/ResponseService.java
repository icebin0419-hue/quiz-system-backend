package com.quiz.quiz_system.service;

import com.quiz.quiz_system.dto.ResponseRequest;
import com.quiz.quiz_system.entity.Response;
import java.util.List;

public interface ResponseService {
    Response submitResponse(Long quizId, ResponseRequest request);
    List<Response> getResponsesByQuizId(Long quizId);
}
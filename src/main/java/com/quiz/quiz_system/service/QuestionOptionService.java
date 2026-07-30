package com.quiz.quiz_system.service;

import com.quiz.quiz_system.entity.QuestionOption;
import java.util.List;

public interface QuestionOptionService {
    List<QuestionOption> getOptionsByQuestionId(Long questionId);
    QuestionOption createOption(Long questionId, QuestionOption option);
    QuestionOption updateOption(Long id, QuestionOption option);
    void deleteOption(Long id);
}
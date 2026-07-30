package com.quiz.quiz_system.service;

import com.quiz.quiz_system.entity.Question;
import com.quiz.quiz_system.entity.QuestionOption;
import com.quiz.quiz_system.repository.QuestionOptionRepository;
import com.quiz.quiz_system.repository.QuestionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionOptionServiceImpl implements QuestionOptionService {

    @Autowired
    private QuestionOptionRepository optionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<QuestionOption> getOptionsByQuestionId(Long questionId) {
        return optionRepository.findByQuestionId(questionId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QuestionOption createOption(Long questionId, QuestionOption option) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("找不到該題目"));
        option.setQuestion(question);
        return optionRepository.save(option);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QuestionOption updateOption(Long id, QuestionOption option) {
        QuestionOption existing = optionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("找不到該選項"));
        existing.setOptionCode(option.getOptionCode());
        existing.setOptionText(option.getOptionText());
        return optionRepository.save(existing);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOption(Long id) {
        if (!optionRepository.existsById(id)) {
            throw new IllegalArgumentException("找不到該選項");
        }
        optionRepository.deleteById(id);
    }
}
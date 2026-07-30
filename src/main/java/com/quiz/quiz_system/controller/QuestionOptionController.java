package com.quiz.quiz_system.controller;

import com.quiz.quiz_system.entity.QuestionOption;
import com.quiz.quiz_system.service.QuestionOptionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions/{questionId}/options")
public class QuestionOptionController {

    @Autowired
    private QuestionOptionService optionService;

    @GetMapping
    public ResponseEntity<List<QuestionOption>> getOptions(@PathVariable Long questionId) {
        return ResponseEntity.ok(optionService.getOptionsByQuestionId(questionId));
    }

    @PostMapping
    public ResponseEntity<QuestionOption> createOption(@PathVariable Long questionId, @RequestBody QuestionOption option) {
        QuestionOption created = optionService.createOption(questionId, option);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionOption> updateOption(@PathVariable Long id, @RequestBody QuestionOption option) {
        return ResponseEntity.ok(optionService.updateOption(id, option));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOption(@PathVariable Long id) {
        optionService.deleteOption(id);
        return ResponseEntity.noContent().build();
    }
}
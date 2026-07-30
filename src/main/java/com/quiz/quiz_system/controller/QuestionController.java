package com.quiz.quiz_system.controller;


import com.quiz.quiz_system.entity.Question;
import com.quiz.quiz_system.service.QuestionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/surveys/{quizId}/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Question>> getQuestions(@PathVariable Long quizId) {
        return ResponseEntity.ok(questionService.getQuestionsByQuizId(quizId));
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@PathVariable Long quizId, @RequestBody Question question) {
        Question created = questionService.createQuestion(quizId, question);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        return ResponseEntity.ok(questionService.updateQuestion(id, question));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
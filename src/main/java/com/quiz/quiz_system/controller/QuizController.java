package com.quiz.quiz_system.controller;

import com.quiz.quiz_system.dto.QuizRequest;
import com.quiz.quiz_system.entity.Quiz;
import com.quiz.quiz_system.service.QuizService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/surveys")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizRequest request) {
        Quiz created = quizService.createQuizWithQuestions(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable("id") Long id, @RequestBody QuizRequest request) {
        return ResponseEntity.ok(quizService.updateQuizWithQuestions(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable("id") Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }
}
package com.quiz.quiz_system.controller;

import com.quiz.quiz_system.dto.ResponseRequest;
import com.quiz.quiz_system.entity.Response;
import com.quiz.quiz_system.service.ResponseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/surveys/{quizId}/responses")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @PostMapping
    public ResponseEntity<Response> submitResponse(@PathVariable Long quizId, @RequestBody ResponseRequest request) {
        Response created = responseService.submitResponse(quizId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Response>> getResponses(@PathVariable Long quizId) {
        return ResponseEntity.ok(responseService.getResponsesByQuizId(quizId));
    }
}
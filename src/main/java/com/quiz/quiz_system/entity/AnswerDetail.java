package com.quiz.quiz_system.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "answer_detail")
public class AnswerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "response_id", nullable = false)
    @JsonBackReference
    private Response response;

    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String answer;

    // ===== Getters / Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Response getResponse() { return response; }
    public void setResponse(Response response) { this.response = response; }

    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) { this.questionId = questionId; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
}
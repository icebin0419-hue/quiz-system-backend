package com.quiz.quiz_system.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "question_option")
public class QuestionOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    @JsonBackReference
    private Question question;

    @Column(name = "option_code")
    private String optionCode;

    @Column(name = "option_text", nullable = false)
    private String optionText;

    // ===== Getters / Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }

    public String getOptionCode() { return optionCode; }
    public void setOptionCode(String optionCode) { this.optionCode = optionCode; }

    @JsonProperty("label")
    public String getOptionText() { return optionText; }
    public void setOptionText(String optionText) { this.optionText = optionText; }
}
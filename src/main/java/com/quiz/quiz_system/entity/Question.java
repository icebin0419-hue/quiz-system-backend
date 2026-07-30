package com.quiz.quiz_system.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    @JsonBackReference
    private Quiz quiz;

    @Column(name = "question_num", nullable = false)
    private Integer questionNum;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String type; // 例如 radio, checkbox, text, scale

    @Column(name = "is_required", nullable = false)
    private Boolean isRequired = true;
    
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<QuestionOption> options = new ArrayList<>();

    // ===== Getters / Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

    public Integer getQuestionNum() { return questionNum; }
    public void setQuestionNum(Integer questionNum) { this.questionNum = questionNum; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Boolean getIsRequired() { return isRequired; }
    public void setIsRequired(Boolean isRequired) { this.isRequired = isRequired; }
    
    public List<QuestionOption> getOptions() { return options; }
    public void setOptions(List<QuestionOption> options) { this.options = options; }
}
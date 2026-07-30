package com.quiz.quiz_system.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "response")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    @JsonBackReference
    private Quiz quiz;

    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    private String email;

    private Integer age;

    @Column(name = "fill_time")
    private LocalDateTime fillTime;

    @OneToMany(mappedBy = "response", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AnswerDetail> answerDetails = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.fillTime = LocalDateTime.now();
    }

    // ===== Getters / Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public LocalDateTime getFillTime() { return fillTime; }
    public void setFillTime(LocalDateTime fillTime) { this.fillTime = fillTime; }

    public List<AnswerDetail> getAnswerDetails() { return answerDetails; }
    public void setAnswerDetails(List<AnswerDetail> answerDetails) { this.answerDetails = answerDetails; }
}
package com.quiz.quiz_system.dto;

import java.util.List;

public class ResponseRequest {
    private String name;
    private String phone;
    private String email;
    private Integer age;
    private List<AnswerRequest> answers;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public List<AnswerRequest> getAnswers() { return answers; }
    public void setAnswers(List<AnswerRequest> answers) { this.answers = answers; }

    public static class AnswerRequest {
        private Long questionId;
        private Object answer; // 可能是 string, string[], number

        public Long getQuestionId() { return questionId; }
        public void setQuestionId(Long questionId) { this.questionId = questionId; }

        public Object getAnswer() { return answer; }
        public void setAnswer(Object answer) { this.answer = answer; }
    }
}
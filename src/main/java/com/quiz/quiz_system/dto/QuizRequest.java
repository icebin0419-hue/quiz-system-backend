package com.quiz.quiz_system.dto;

import java.util.List;

public class QuizRequest {
    private String title;
    private String description;
    private Boolean isPublished;
    private List<QuestionRequest> questions;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getIsPublished() { return isPublished; }
    public void setIsPublished(Boolean isPublished) { this.isPublished = isPublished; }

    public List<QuestionRequest> getQuestions() { return questions; }
    public void setQuestions(List<QuestionRequest> questions) { this.questions = questions; }

    public static class QuestionRequest {
        private String title;
        private String type;
        private Boolean required;
        private List<OptionRequest> options;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public Boolean getRequired() { return required; }
        public void setRequired(Boolean required) { this.required = required; }

        public List<OptionRequest> getOptions() { return options; }
        public void setOptions(List<OptionRequest> options) { this.options = options; }
    }

    public static class OptionRequest {
        private String label;

        public String getLabel() { return label; }
        public void setLabel(String label) { this.label = label; }
    }
}
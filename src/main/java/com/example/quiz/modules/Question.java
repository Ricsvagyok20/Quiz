package com.example.quiz.modules;

import lombok.Data;
@Data
public class Question {
    private int questionId;
    private String questionContent;
    private String subtopicName;

    public Question(int questionId, String questionContent, String subtopicName) {
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.subtopicName = subtopicName;
    }
}

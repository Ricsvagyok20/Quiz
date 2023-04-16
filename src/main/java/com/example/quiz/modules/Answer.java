package com.example.quiz.modules;

import lombok.Data;
@Data
public class Answer {
    private int answerId;
    private int questionId;
    private String answerContent;

    public Answer(int answerId, int questionId, String answerContent) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.answerContent = answerContent;
    }
}

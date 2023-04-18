package com.example.quiz.modules;

import lombok.Data;
@Data
public class Answer {
    private int answerId;
    private int questionId;
    private String answerContent;
    private String correctAnswer;

    public Answer(int answerId, int questionId, String answerContent, String correctAnswer) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.answerContent = answerContent;
        this.correctAnswer = correctAnswer;
    }
}

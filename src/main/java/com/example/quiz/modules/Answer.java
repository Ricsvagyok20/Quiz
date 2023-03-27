package com.example.quiz.modules;

import lombok.Data;
@Data
public class Answer {
    private int answerId;
    private String answerContent;
    private boolean correct;

    public Answer(int answerId, String answerContent, boolean correct) {
        this.answerId = answerId;
        this.answerContent = answerContent;
        this.correct = correct;
    }
}

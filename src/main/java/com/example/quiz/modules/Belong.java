package com.example.quiz.modules;

import lombok.Data;
@Data
public class Belong {
    private int quizId;
    private int answerId;

    public Belong(int quizId, int answerId) {
        this.quizId = quizId;
        this.answerId = answerId;
    }
}

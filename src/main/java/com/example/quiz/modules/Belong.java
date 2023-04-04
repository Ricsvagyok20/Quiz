package com.example.quiz.modules;

import lombok.Data;
@Data
public class Belong {
    private int quizIdBelong;
    private int answerIdBelong;

    public Belong(int quizId, int answerId) {
        this.quizIdBelong = quizId;
        this.answerIdBelong = answerId;
    }
}

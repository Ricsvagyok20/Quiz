package com.example.quiz.modules;

import lombok.Data;
@Data
public class Ask{
    private int quetionId;
    private int quizId;

    public Ask(int quetionId, int quizId) {
        this.quetionId = quetionId;
        this.quizId = quizId;
    }
}

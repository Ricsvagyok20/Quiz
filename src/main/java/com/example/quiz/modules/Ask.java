package com.example.quiz.modules;

import lombok.Data;
@Data
public class Ask{
    private int questionIdAsk;
    private int quizIdAsk;

    public Ask(int quetionId, int quizId) {
        this.questionIdAsk = quetionId;
        this.quizIdAsk = quizId;
    }
}

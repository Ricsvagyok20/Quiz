package com.example.quiz.modules;

import lombok.Data;
@Data
public class Play {
    private String userName;
    private int quizId;

    public Play(String userName, int quizId) {
        this.userName = userName;
        this.quizId = quizId;
    }
}

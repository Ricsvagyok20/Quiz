package com.example.quiz.modules;

import lombok.Data;
@Data
public class Play {
    private String userNamePlay;
    private int quizIdPlay;

    public Play(String userName, int quizId) {
        this.userNamePlay = userName;
        this.quizIdPlay = quizId;
    }
}

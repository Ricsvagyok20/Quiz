package com.example.quiz.modules;

import lombok.Data;
@Data
public class Quiz {
    private int quizId;
    private String topicName;

    public Quiz(int quizId, String topicName) {
        this.quizId = quizId;
        this.topicName = topicName;
    }
}

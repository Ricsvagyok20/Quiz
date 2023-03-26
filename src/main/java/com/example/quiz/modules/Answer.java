package com.example.quiz.modules;

import lombok.Data;
@Data
public class Answer {
    private int answerId;
    private String answerContent;
    private boolean correct;
}

package com.example.quiz.modules;

import lombok.Data;

@Data
public class QuestionCount {
    String description;
    int count;

    public QuestionCount(String description, int count) {
        this.description = description;
        this.count = count;
    }
}

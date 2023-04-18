package com.example.quiz.modules;

import lombok.Data;
@Data
public class Question {
    private int Id;
    private String questionContent;
    private String subtopicName;


    public Question(int Id, String questionContent, String subtopicName) {
        this.Id = Id;
        this.questionContent = questionContent;
        this.subtopicName = subtopicName;
    }
}

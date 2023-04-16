package com.example.quiz.modules;

import lombok.Data;
@Data
public class Question {
    private int Id;
    private String questionContent;
    private String subtopicName;
    private int correctAnswer;

    public Question(int Id, String questionContent, String subtopicName, int correctAnswer) {
        this.Id = Id;
        this.questionContent = questionContent;
        this.subtopicName = subtopicName;
        this.correctAnswer = correctAnswer;
    }
}

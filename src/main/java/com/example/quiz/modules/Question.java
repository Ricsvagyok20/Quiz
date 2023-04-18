package com.example.quiz.modules;

import lombok.Data;
@Data
public class Question {
    private int Id;
    private String questionContent;
    private String subtopicNameQuestion;


    public Question(int Id, String questionContent, String subtopicNameQuestion) {
        this.Id = Id;
        this.questionContent = questionContent;
        this.subtopicNameQuestion = subtopicNameQuestion;
    }

    public Question(String questionContent, String subtopicNameQuestion) {
        this.Id = 0;
        this.questionContent = questionContent;
        this.subtopicNameQuestion = subtopicNameQuestion;
    }
}

package com.example.quiz.modules;

import lombok.Data;
@Data
public class Subtopic {
    private String subtopicName;
    private String description;
    private String topicName;

    public Subtopic(String subtopicName, String description, String topicName) {
        this.subtopicName = subtopicName;
        this.description = description;
        this.topicName = topicName;
    }
}

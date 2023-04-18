package com.example.quiz.modules;

import lombok.Data;
@Data
public class Subtopic {
    private String subtopicName;
    private String description;
    private String topicNameSubtopic;

    public Subtopic(String subtopicName, String description, String topicNameSubtopic) {
        this.subtopicName = subtopicName;
        this.description = description;
        this.topicNameSubtopic = topicNameSubtopic;
    }
}

package com.example.quiz.modules;

import lombok.Data;

@Data
public class SubtopicDescByTopic {
    String topicName;
    String subtopicName;
    String description;

    public SubtopicDescByTopic(String topicName, String subtopicName, String description) {
        this.topicName = topicName;
        this.subtopicName = subtopicName;
        this.description = description;
    }
}

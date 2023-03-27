package com.example.quiz.modules;

import lombok.Data;
@Data
public class Topic {
    private String topicName;

    public Topic(String topicName) {
        this.topicName = topicName;
    }
}

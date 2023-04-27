package com.example.quiz.modules;

import lombok.Data;

@Data
public class RankingByTopic {
    String topic;
    float point;

    public RankingByTopic(String topic, float point) {
        this.topic = topic;
        this.point = point;
    }
}

package com.example.quiz.modules;

import lombok.Data;

@Data
public class RankingByTopic {
    String user;
    String topic;
    float point;

    public RankingByTopic(String user, String topic, float point) {
        this.user = user;
        this.topic = topic;
        this.point = point;
    }
}

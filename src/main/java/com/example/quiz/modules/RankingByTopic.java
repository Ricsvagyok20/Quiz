package com.example.quiz.modules;

import lombok.Data;

@Data
public class RankingByTopic {
    String user;
    String topic;
    int point;

    public RankingByTopic(String user, String topic, int point) {
        this.user = user;
        this.topic = topic;
        this.point = point;
    }
}

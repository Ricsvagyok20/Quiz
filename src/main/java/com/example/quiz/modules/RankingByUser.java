package com.example.quiz.modules;

import lombok.Data;

@Data
public class RankingByUser {
    String rankingUser;
    float averagePoint;

    public RankingByUser(String rankingUser, float averagePoint) {
        this.rankingUser = rankingUser;
        this.averagePoint = averagePoint;
    }
}

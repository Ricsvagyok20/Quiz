package com.example.quiz.modules;

import lombok.Data;

@Data
public class RankingPoint {
    private String userNameRankingPoint;
    private String topicNameRankingPoint;
    private int rankingPoint;

    public RankingPoint(String userNameRankingPoint, String topicNameRankingPoint, int rankingPoint) {
        this.userNameRankingPoint = userNameRankingPoint;
        this.topicNameRankingPoint = topicNameRankingPoint;
        this.rankingPoint = rankingPoint;
    }
}

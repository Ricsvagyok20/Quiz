package com.example.quiz.modules;

import lombok.Data;

@Data
public class RankingPoint {
    private int IdRankingPoint;
    private String userNameRankingPoint;
    private String topicNameRankingPoint;
    private int rankingPoint;

    public RankingPoint(String userNameRankingPoint, String topicNameRankingPoint, int rankingPoint) {
        this.userNameRankingPoint = userNameRankingPoint;
        this.topicNameRankingPoint = topicNameRankingPoint;
        this.rankingPoint = rankingPoint;
        this.IdRankingPoint= 0;
    }
    public RankingPoint(int Id, String userNameRankingPoint, String topicNameRankingPoint, int rankingPoint) {
        this.IdRankingPoint= Id;
        this.userNameRankingPoint = userNameRankingPoint;
        this.topicNameRankingPoint = topicNameRankingPoint;
        this.rankingPoint = rankingPoint;
    }
}

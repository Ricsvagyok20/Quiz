package com.example.quiz.modules;

import lombok.Data;
@Data
public class Player{
    private String userName;
    private String password;
    private String email;
    private int rankingPoints;
    private String topicName;

    public Player(String userName, String password, String email, int rankingPoints, String topicName) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.rankingPoints = rankingPoints;
        this.topicName = topicName;
    }
}

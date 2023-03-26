package com.example.quiz.modules;

import lombok.Data;
@Data
public class Player{
    private String userName;
    private String password;
    private String email;
    private int rankingPoints;
    private String topicName;
}

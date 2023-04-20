package com.example.quiz.modules;

import lombok.Data;
@Data
public class Player{
    private String userName;
    private String password;
    private String email;
    private String topicNamePlayer;

    public Player(String userName, String password, String email, String topicName) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.topicNamePlayer = topicName;
    }
}

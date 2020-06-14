package com.example.project4210.models;

public class LeaderboardModel {
    private String rank, username, score;

    public LeaderboardModel(String rank, String username, String score) {
        this.rank = rank;
        this.username = username;
        this.score = score;
    }

    public String getRank() {
        return rank;
    }

    public String getUsername() {
        return username;
    }

    public String getScore() {
        return score;
    }
}

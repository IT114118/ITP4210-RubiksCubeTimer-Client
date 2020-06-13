package com.example.project4210.models;

// Mysql

public class UserModel {
    private String username;

    private int globalRank;
    private int numberOfSolve;
    private float personalBest;
    private float ao5;
    private float ao12;
    private float average;

    public UserModel(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", globalRank=" + globalRank +
                ", numberOfSolve=" + numberOfSolve +
                ", personalBest=" + personalBest +
                ", ao5=" + ao5 +
                ", ao12=" + ao12 +
                ", average=" + average +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public int getNumberOfSolve() {
        return numberOfSolve;
    }

    public void setNumberOfSolve(int numberOfSolve) {
        this.numberOfSolve = numberOfSolve;
    }

    public float getPersonalBest() {
        return personalBest;
    }

    public void setPersonalBest(float personalBest) {
        this.personalBest = personalBest;
    }

    public float getAo5() {
        return ao5;
    }

    public void setAo5(float ao5) {
        this.ao5 = ao5;
    }

    public float getAo12() {
        return ao12;
    }

    public void setAo12(float ao12) {
        this.ao12 = ao12;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public int getGlobalRank() {
        return globalRank;
    }

    public void setGlobalRank(int globalRank) {
        this.globalRank = globalRank;
    }
}

package com.example.project4210.models;

// SQLite

public class RecordModel {

    private int id;
    private float time;
    private String scramble;
    private boolean isStarred;

    public RecordModel(float time, String scramble) {
        this.time = time;
        this.scramble = scramble;
        this.isStarred = false;
    }

    public RecordModel(float time, String scramble, boolean isStarred) {
        this.time = time;
        this.scramble = scramble;
        this.isStarred = isStarred;
    }

    @Override
    public String toString() {
        return "RecordModel{" +
                "id=" + id +
                ", time=" + time +
                ", scramble='" + scramble + '\'' +
                ", isStarred=" + isStarred +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getScramble() {
        return scramble;
    }

    public void setScramble(String scramble) {
        this.scramble = scramble;
    }

    public boolean isStarred() {
        return isStarred;
    }

    public void setStarred(boolean starred) {
        isStarred = starred;
    }
}

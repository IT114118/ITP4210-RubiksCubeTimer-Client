package com.example.project4210.models;

public class RecordModel {

    private float time;
    private String scramble;
    private boolean isStarred;

    public RecordModel(float time, String scramble, boolean isStarred) {
        this.time = time;
        this.scramble = scramble;
        this.isStarred = isStarred;
    }

    @Override
    public String toString() {
        return "RecordModel{" +
                "time=" + time +
                ", scramble='" + scramble + '\'' +
                ", isStarred=" + isStarred +
                '}';
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

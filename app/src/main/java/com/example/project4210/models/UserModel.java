package com.example.project4210.models;

public class UserModel {

    private int id;
    private String name;
    private String password;

    private float personalBest;
    private float ao5;
    private float ao12;
    private float average;

    public UserModel(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", personalBest=" + personalBest +
                ", ao5=" + ao5 +
                ", ao12=" + ao12 +
                ", average=" + average +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}

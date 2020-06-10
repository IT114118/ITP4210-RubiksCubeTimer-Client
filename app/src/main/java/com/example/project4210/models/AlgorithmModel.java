package com.example.project4210.models;



public class AlgorithmModel {

    //e.g. oll_1, pll_12
    private String caseId;
    private String algorithmModel;
    private String byUser;
    private int votes;

    public AlgorithmModel(String caseId, String algorithmModel, String byUser) {
        this.caseId = caseId;
        this.algorithmModel = algorithmModel;
        this.byUser = byUser;
        this.votes = 0;
    }

    @Override
    public String toString() {
        return "AlgorithmModel{" +
                "caseId='" + caseId + '\'' +
                ", algorithmModel='" + algorithmModel + '\'' +
                ", byUser='" + byUser + '\'' +
                ", votes=" + votes +
                '}';
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getAlgorithmModel() {
        return algorithmModel;
    }

    public void setAlgorithmModel(String algorithmModel) {
        this.algorithmModel = algorithmModel;
    }

    public String getByUser() {
        return byUser;
    }

    public void setByUser(String byUser) {
        this.byUser = byUser;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}

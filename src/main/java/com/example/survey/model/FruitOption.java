package com.example.survey.model;

/**
 * 과일별 투표 집계 결과를 화면으로 전달하기 위한 모델 클래스.
 */
public class FruitOption {

    // 데이터베이스의 과일 번호.
    private int id;

    // 화면에 표시할 과일 이름.
    private String name;

    // 해당 과일의 누적 투표 수.
    private int voteCount;

    // 전체 투표 수 대비 백분율 값.
    private double voteRate;

    public FruitOption() {
    }

    public FruitOption(int id, String name, int voteCount, double voteRate) {
        this.id = id;
        this.name = name;
        this.voteCount = voteCount;
        this.voteRate = voteRate;
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

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public double getVoteRate() {
        return voteRate;
    }

    public void setVoteRate(double voteRate) {
        this.voteRate = voteRate;
    }
}

package com.example.WhoZScore.data.entities;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/30/15
 * Time: 10:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractZScoreEntity {
    private int id;

    private double threeScore;
    private double twoScore;
    private double oneScore;
    private double zeroScore;
    private double minusOneScore;
    private double minusTwoScore;
    private double minusThreeScore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public double getThreeScore() {
        return threeScore;
    }

    public void setThreeScore(double threeScore) {
        this.threeScore = threeScore;
    }

    public double getTwoScore() {
        return twoScore;
    }

    public void setTwoScore(double twoScore) {
        this.twoScore = twoScore;
    }

    public double getOneScore() {
        return oneScore;
    }

    public void setOneScore(double oneScore) {
        this.oneScore = oneScore;
    }

    public double getZeroScore() {
        return zeroScore;
    }

    public void setZeroScore(double zeroScore) {
        this.zeroScore = zeroScore;
    }

    public double getMinusOneScore() {
        return minusOneScore;
    }

    public void setMinusOneScore(double minusOneScore) {
        this.minusOneScore = minusOneScore;
    }

    public double getMinusTwoScore() {
        return minusTwoScore;
    }

    public void setMinusTwoScore(double minusTwoScore) {
        this.minusTwoScore = minusTwoScore;
    }

    public double getMinusThreeScore() {
        return minusThreeScore;
    }

    public void setMinusThreeScore(double minusThreeScore) {
        this.minusThreeScore = minusThreeScore;
    }
}

package com.example.WhoZScore.model;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/29/15
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class WeightForAgeResult implements IResult{
    private boolean isHealthy;
    private String zScoreWeightMessage;
    private String healthyWeightMessage;


    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

    public String getzScoreWeightMessage() {
        return zScoreWeightMessage;
    }

    public void setzScoreWeightMessage(String zScoreWeightMessage) {
        this.zScoreWeightMessage = zScoreWeightMessage;
    }

    public String getHealthyWeightMessage() {
        return healthyWeightMessage;
    }

    public void setHealthyWeightMessage(String healthyWeightMessage) {
        this.healthyWeightMessage = healthyWeightMessage;
    }

}

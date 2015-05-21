package com.example.WhoZScore.model;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/29/15
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class WeightForHeightResult implements IResult{
    private boolean isHealthy;
    private String zScoreWeightForHeightMessage;
    private String healthyWeightForHeightMessage;


    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

    public String getzScoreWeightForHeightMessage() {
        return zScoreWeightForHeightMessage;
    }

    public void setzScoreWeightForHeightMessage(String zScoreWeightForHeightMessage) {
        this.zScoreWeightForHeightMessage = zScoreWeightForHeightMessage;
    }

    public String getHealthyWeightForHeightMessage() {
        return healthyWeightForHeightMessage;
    }

    public void setHealthyWeightForHeightMessage(String healthyWeightForHeightMessage) {
        this.healthyWeightForHeightMessage = healthyWeightForHeightMessage;
    }

}

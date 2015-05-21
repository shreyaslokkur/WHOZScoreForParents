package com.example.WhoZScore.model;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/29/15
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeightForAgeResult implements IResult {
    private boolean isHealthy;
    private String zScoreHeightMessage;
    private String healthyHeightMessage;

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }


    public String getzScoreHeightMessage() {
        return zScoreHeightMessage;
    }

    public void setzScoreHeightMessage(String zScoreHeightMessage) {
        this.zScoreHeightMessage = zScoreHeightMessage;
    }

    public String getHealthyHeightMessage() {
        return healthyHeightMessage;
    }

    public void setHealthyHeightMessage(String healthyHeightMessage) {
        this.healthyHeightMessage = healthyHeightMessage;
    }
}

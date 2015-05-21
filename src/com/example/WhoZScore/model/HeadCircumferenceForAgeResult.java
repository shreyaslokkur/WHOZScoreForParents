package com.example.WhoZScore.model;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/29/15
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeadCircumferenceForAgeResult implements IResult {
    private boolean isHealthy;
    private String zScoreHeadCircumferenceMessage;
    private String healthyHeadCircumferenceMessage;

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }


    public String getzScoreHeadCircumferenceMessage() {
        return zScoreHeadCircumferenceMessage;
    }

    public void setzScoreHeadCircumferenceMessage(String zScoreHeadCircumferenceMessage) {
        this.zScoreHeadCircumferenceMessage = zScoreHeadCircumferenceMessage;
    }

    public String getHealthyHeadCircumferenceMessage() {
        return healthyHeadCircumferenceMessage;
    }

    public void setHealthyHeadCircumferenceMessage(String healthyHeadCircumferenceMessage) {
        this.healthyHeadCircumferenceMessage = healthyHeadCircumferenceMessage;
    }
}

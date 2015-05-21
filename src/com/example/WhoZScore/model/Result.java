package com.example.WhoZScore.model;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/1/15
 * Time: 11:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Result implements IResult {

    private boolean isHealthy;
    private String zScoreWeightForAgeMessage;
    private String healthyWeightForAgeMessage;
    private String zScoreHeightForAgeMessage;
    private String healthyHeightForAgeMessage;
    private String zScoreWeightForHeightMessage;
    private String healthyWeightForHeightMessage;
    private String zScoreHeadCircumferenceForAgeMessage;
    private String healthyHeadCircumferenceForAgeMessage;

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

    public String getzScoreWeightForAgeMessage() {
        return zScoreWeightForAgeMessage;
    }

    public void setzScoreWeightForAgeMessage(String zScoreWeightForAgeMessage) {
        this.zScoreWeightForAgeMessage = zScoreWeightForAgeMessage;
    }

    public String getHealthyWeightForAgeMessage() {
        return healthyWeightForAgeMessage;
    }

    public void setHealthyWeightForAgeMessage(String healthyWeightForAgeMessage) {
        this.healthyWeightForAgeMessage = healthyWeightForAgeMessage;
    }

    public String getzScoreHeightForAgeMessage() {
        return zScoreHeightForAgeMessage;
    }

    public void setzScoreHeightForAgeMessage(String zScoreHeightForAgeMessage) {
        this.zScoreHeightForAgeMessage = zScoreHeightForAgeMessage;
    }

    public String getHealthyHeightForAgeMessage() {
        return healthyHeightForAgeMessage;
    }

    public void setHealthyHeightForAgeMessage(String healthyHeightForAgeMessage) {
        this.healthyHeightForAgeMessage = healthyHeightForAgeMessage;
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

    public String getzScoreHeadCircumferenceForAgeMessage() {
        return zScoreHeadCircumferenceForAgeMessage;
    }

    public void setzScoreHeadCircumferenceForAgeMessage(String zScoreHeadCircumferenceForAgeMessage) {
        this.zScoreHeadCircumferenceForAgeMessage = zScoreHeadCircumferenceForAgeMessage;
    }

    public String getHealthyHeadCircumferenceForAgeMessage() {
        return healthyHeadCircumferenceForAgeMessage;
    }

    public void setHealthyHeadCircumferenceForAgeMessage(String healthyHeadCircumferenceForAgeMessage) {
        this.healthyHeadCircumferenceForAgeMessage = healthyHeadCircumferenceForAgeMessage;
    }
}

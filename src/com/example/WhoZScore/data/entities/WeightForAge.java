package com.example.WhoZScore.data.entities;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/26/15
 * Time: 6:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class WeightForAge extends AbstractZScoreEntity implements IZScoreEntity {

    private int weeks;
    private int months;
    private int years;

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }
}

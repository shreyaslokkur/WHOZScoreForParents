package com.example.WhoZScore.enums;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/6/15
 * Time: 11:02 PM
 * To change this template use File | Settings | File Templates.
 */
public enum AgeGroup {

    WEEKS(0,"Age in Weeks"),
    TILLONEYEAR(1,"Age in Months"),
    TILLTWOYEARS(2,"Age in Months"),
    TILLTHREEYEARS(3,"Age in Months"),
    TILLFOURYEARS(4,"Age in Months"),
    TILLFIVEYEARS(5,"Age in Months");

    private int maxYears;
    private String xAxis;


    private AgeGroup(int maxYears, String xAxis) {
        this.maxYears = maxYears;
        this.xAxis = xAxis;
    }

    public int getMaxYears() {
        return maxYears;
    }

    public String getxAxis() {
        return xAxis;
    }
}

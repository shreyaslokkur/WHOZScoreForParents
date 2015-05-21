package com.example.WhoZScore.enums;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/5/15
 * Time: 7:35 PM
 * To change this template use File | Settings | File Templates.
 */
public enum ZScoreGraphTypes {

    WEIGHT_FOR_AGE_BOYS("Weight for Age Boys","Weight (kg)","age"),
    WEIGHT_FOR_AGE_GIRLS("Weight for Age Girls","Weight (kg)","age"),
    HEIGHT_FOR_AGE_BOYS("Height for Age Boys","Height (cms)","age"),
    HEIGHT_FOR_AGE_GIRLS("Height for Age Girls","Height (cms)","age"),
    WEIGHT_FOR_HEIGHT_BOYS("Weight for Height Boys","Weight (kg)","height"),
    WEIGHT_FOR_HEIGHT_GIRLS("Weight for Height Girls","Weight (kg)","height"),
    HEAD_CIRCUMFERENCE_FOR_AGE_BOYS("Head circumference for Age Boys", "Height (cms)","age"),
    HEAD_CIRCUMFERENCE_FOR_AGE_GIRLS("Head circumference for Age Girls", "Height (cms)","age");

    private String graphNames;
    private String yAxis;
    private String xAxisType;
    private ZScoreGraphTypes(String graphNames, String yAxis, String xAxisType) {
        this.graphNames = graphNames;
        this.yAxis = yAxis;
        this.xAxisType = xAxisType;
    }

    public String getGraphNames() {
        return graphNames;
    }

    public String getyAxis() {
        return yAxis;
    }

    public String getxAxisType() {
        return xAxisType;
    }
}

package com.example.WhoZScore.model;

import com.example.WhoZScore.enums.Age;
import com.example.WhoZScore.enums.AgeGroup;
import com.example.WhoZScore.enums.ZScoreGraphTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/5/15
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class GraphModel {
    private List<Double> minusThreeScore = new ArrayList<Double>();
    private List<Double> minusTwoScore = new ArrayList<Double>();
    private List<Double> minusOneScore = new ArrayList<Double>();
    private List<Double> zeroScore = new ArrayList<Double>();
    private List<Double> oneScore = new ArrayList<Double>();
    private List<Double> twoScore = new ArrayList<Double>();
    private List<Double> threeScore = new ArrayList<Double>();
    private List<Integer> xAxis = new ArrayList<Integer>();
    private List<String> xAxisTextLabels = new ArrayList<String>();
    private Age age;
    private ZScoreGraphTypes zScoreGraphTypes;
    private double patientWeight;
    private double patientHeight;
    private double patientHeadCircumference;
    private int ageInWeeks;
    private int ageInMonths;
    private int ageInYears;

    private int yMin;
    private int yMax;

    private int xMin;
    private int xMax;

    private AgeGroup ageGroup;

    public List<Double> getMinusThreeScore() {
        return minusThreeScore;
    }

    public void setMinusThreeScore(List<Double> minusThreeScore) {
        this.minusThreeScore = minusThreeScore;
    }

    public List<Double> getMinusTwoScore() {
        return minusTwoScore;
    }

    public void setMinusTwoScore(List<Double> minusTwoScore) {
        this.minusTwoScore = minusTwoScore;
    }

    public List<Double> getMinusOneScore() {
        return minusOneScore;
    }

    public void setMinusOneScore(List<Double> minusOneScore) {
        this.minusOneScore = minusOneScore;
    }

    public List<Double> getZeroScore() {
        return zeroScore;
    }

    public void setZeroScore(List<Double> zeroScore) {
        this.zeroScore = zeroScore;
    }

    public List<Double> getOneScore() {
        return oneScore;
    }

    public void setOneScore(List<Double> oneScore) {
        this.oneScore = oneScore;
    }

    public List<Double> getTwoScore() {
        return twoScore;
    }

    public void setTwoScore(List<Double> twoScore) {
        this.twoScore = twoScore;
    }

    public List<Double> getThreeScore() {
        return threeScore;
    }

    public void setThreeScore(List<Double> threeScore) {
        this.threeScore = threeScore;
    }

    public List<Integer> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<Integer> xAxis) {
        this.xAxis = xAxis;
    }

    public List<String> getxAxisTextLabels() {
        return xAxisTextLabels;
    }

    public void setxAxisTextLabels(List<String> xAxisTextLabels) {
        this.xAxisTextLabels = xAxisTextLabels;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public ZScoreGraphTypes getzScoreGraphTypes() {
        return zScoreGraphTypes;
    }

    public void setzScoreGraphTypes(ZScoreGraphTypes zScoreGraphTypes) {
        this.zScoreGraphTypes = zScoreGraphTypes;
    }

    public double getPatientWeight() {
        return patientWeight;
    }

    public void setPatientWeight(double patientWeight) {
        this.patientWeight = patientWeight;
    }

    public double getPatientHeight() {
        return patientHeight;
    }

    public void setPatientHeight(double patientHeight) {
        this.patientHeight = patientHeight;
    }

    public double getPatientHeadCircumference() {
        return patientHeadCircumference;
    }

    public void setPatientHeadCircumference(double patientHeadCircumference) {
        this.patientHeadCircumference = patientHeadCircumference;
    }

    public int getyMin() {
        return yMin;
    }

    public void setyMin(int yMin) {
        this.yMin = yMin;
    }

    public int getyMax() {
        return yMax;
    }

    public void setyMax(int yMax) {
        this.yMax = yMax;
    }

    public int getxMin() {
        return xMin;
    }

    public void setxMin(int xMin) {
        this.xMin = xMin;
    }

    public int getxMax() {
        return xMax;
    }

    public void setxMax(int xMax) {
        this.xMax = xMax;
    }

    public int getAgeInWeeks() {
        return ageInWeeks;
    }

    public void setAgeInWeeks(int ageInWeeks) {
        this.ageInWeeks = ageInWeeks;
    }

    public int getAgeInMonths() {
        return ageInMonths;
    }

    public void setAgeInMonths(int ageInMonths) {
        this.ageInMonths = ageInMonths;
    }

    public int getAgeInYears() {
        return ageInYears;
    }

    public void setAgeInYears(int ageInYears) {
        this.ageInYears = ageInYears;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }
}

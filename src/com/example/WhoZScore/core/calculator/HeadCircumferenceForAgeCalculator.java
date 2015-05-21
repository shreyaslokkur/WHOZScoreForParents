package com.example.WhoZScore.core.calculator;

import android.content.Context;
import com.example.WhoZScore.data.dao.HeadCircumferenceForAgeDataSource;
import com.example.WhoZScore.data.dao.IZScoreDataSource;
import com.example.WhoZScore.data.entities.HeadCircumferenceForAge;
import com.example.WhoZScore.data.entities.IZScoreEntity;
import com.example.WhoZScore.enums.Age;
import com.example.WhoZScore.enums.AgeGroup;
import com.example.WhoZScore.enums.Sex;
import com.example.WhoZScore.enums.ZScoreGraphTypes;
import com.example.WhoZScore.model.GraphModel;
import com.example.WhoZScore.model.Patient;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/9/15
 * Time: 12:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class HeadCircumferenceForAgeCalculator extends AbstractCalculator  {

    IZScoreDataSource headCircumferenceForAgeDataSource;

    @Override
    public IZScoreEntity calculateZScore(Patient patient, Context context) {
        headCircumferenceForAgeDataSource = new HeadCircumferenceForAgeDataSource(context);
        HeadCircumferenceForAge headCircumferenceForAge = (HeadCircumferenceForAge) headCircumferenceForAgeDataSource.getScore(patient.getAgeInWeeks(), patient.getAgeInMonths(), patient.getAgeInYears(), patient.getSex());

        return headCircumferenceForAge;
    }

    @Override
    public GraphModel getGraphModel(Patient patient, ZScoreGraphTypes zScoreGraphTypes, Context context) {
        headCircumferenceForAgeDataSource = new HeadCircumferenceForAgeDataSource(context);

        Age age= Age.MONTHS;
        AgeGroup ageGroup = null;
        GraphModel graphModel = null;
        if(patient.getAgeInWeeks() == 0 && patient.getAgeInMonths() == 0 && patient.getAgeInYears() == 0){
            ageGroup = AgeGroup.WEEKS;
            age = Age.WEEKS;
        }else if(patient.getAgeInWeeks() > 0)  {
            ageGroup = AgeGroup.WEEKS;
            age = Age.WEEKS;
        }else if(patient.getAgeInMonths() > 0 && (patient.getAgeInYears() >= 0 && patient.getAgeInYears() < 1)){
            ageGroup = AgeGroup.TILLONEYEAR;
        }else if(patient.getAgeInMonths() >= 0 && (patient.getAgeInYears() >=1 && patient.getAgeInYears() <2)){
            ageGroup = AgeGroup.TILLTWOYEARS;
        }else if(patient.getAgeInMonths() >= 0 && (patient.getAgeInYears() >= 2 && patient.getAgeInYears() < 3)){
            ageGroup = AgeGroup.TILLTHREEYEARS;
        }else if(patient.getAgeInMonths() >= 0 && (patient.getAgeInYears() >= 3 && patient.getAgeInYears() < 4)){
            ageGroup = AgeGroup.TILLFOURYEARS;
        }else {
            ageGroup = AgeGroup.TILLFIVEYEARS;
        }

        List<Integer> xAxis = createXAxis(age, ageGroup.getMaxYears());
        List<String> xAxisTextLabels = createXTextLabels(ageGroup);
        List<HeadCircumferenceForAge> headCircumferenceForAgeList = null;

        int minGraphXAxis = createXMin(patient);
        int maxGraphXAxis = createXMax(patient);

        switch (zScoreGraphTypes){
            case HEAD_CIRCUMFERENCE_FOR_AGE_BOYS:
                headCircumferenceForAgeList = getScoreRange(ageGroup, patient.getSex(), zScoreGraphTypes);
                break;
            case HEAD_CIRCUMFERENCE_FOR_AGE_GIRLS:
                headCircumferenceForAgeList = getScoreRange(ageGroup, patient.getSex(), zScoreGraphTypes);
                break;

        }

        graphModel = createGraphModelForHeadCircumference(headCircumferenceForAgeList, zScoreGraphTypes, age, patient);
        graphModel.setxAxis(xAxis);
        graphModel.setxMax(maxGraphXAxis);
        graphModel.setxMin(minGraphXAxis);
        graphModel.setxAxisTextLabels(xAxisTextLabels);
        graphModel.setAgeInWeeks(patient.getAgeInWeeks());
        graphModel.setAgeInMonths(patient.getAgeInMonths());
        graphModel.setAgeInYears(patient.getAgeInYears());
        graphModel.setAgeGroup(ageGroup);


        return graphModel;
    }

    @Override
    public List getScoreRange(int minWeeks, int maxWeeks, int minMonths, int maxMonths, int minYears, int maxYears, Sex sex, ZScoreGraphTypes zScoreGraphTypes) {

        List<HeadCircumferenceForAge> scoreRangeForHeadCircumference = null;

        switch (zScoreGraphTypes){
            case HEAD_CIRCUMFERENCE_FOR_AGE_BOYS:
                scoreRangeForHeadCircumference = headCircumferenceForAgeDataSource.getScoreRange(minWeeks, maxWeeks, minMonths, maxMonths, minYears, maxYears, Sex.MALE);
                break;
            case HEAD_CIRCUMFERENCE_FOR_AGE_GIRLS:
                scoreRangeForHeadCircumference = headCircumferenceForAgeDataSource.getScoreRange(minWeeks, maxWeeks, minMonths, maxMonths, minYears, maxYears, Sex.FEMALE);
                break;

        }
        return scoreRangeForHeadCircumference;
    }

    private GraphModel createGraphModelForHeadCircumference(List<HeadCircumferenceForAge> scoreRangeForHeadCircumference, ZScoreGraphTypes zScoreGraphTypes, Age age, Patient patient) {
        GraphModel graphModel = new GraphModel();
        graphModel.setzScoreGraphTypes(zScoreGraphTypes);
        graphModel.setAge(age);
        Double minusThreeScoreDoubleValue = new Double(scoreRangeForHeadCircumference.get(0).getMinusThreeScore());
        Double maxThreeScoreDoubleValue = new Double(scoreRangeForHeadCircumference.get(scoreRangeForHeadCircumference.size() - 1).getThreeScore());
        graphModel.setyMin( minusThreeScoreDoubleValue.intValue() - 10);
        graphModel.setyMax(maxThreeScoreDoubleValue.intValue() + 10);
        for(HeadCircumferenceForAge headCircumferenceForAge : scoreRangeForHeadCircumference){
            graphModel.getMinusThreeScore().add(headCircumferenceForAge.getMinusThreeScore());
            graphModel.getMinusTwoScore().add(headCircumferenceForAge.getMinusTwoScore());
            graphModel.getMinusOneScore().add(headCircumferenceForAge.getMinusOneScore());
            graphModel.getZeroScore().add(headCircumferenceForAge.getZeroScore());
            graphModel.getOneScore().add(headCircumferenceForAge.getOneScore());
            graphModel.getTwoScore().add(headCircumferenceForAge.getTwoScore());
            graphModel.getThreeScore().add(headCircumferenceForAge.getThreeScore());

        }

        graphModel.setPatientHeadCircumference(patient.getHeadCircumference());

        return graphModel;
    }

    private int createXMax(Patient patient) {
        if(patient.getAgeInWeeks() > 0 || patient.getAgeInYears() > 4 ){
            return 13;
        }else {
            return 12;
        }
    }

    private int createXMin(Patient patient) {

        return -1;
    }
}

package com.example.WhoZScore.core.calculator;

import android.content.Context;
import com.example.WhoZScore.data.dao.IZScoreDataSource;
import com.example.WhoZScore.data.dao.WeightForAgeDataSource;
import com.example.WhoZScore.data.entities.IZScoreEntity;
import com.example.WhoZScore.data.entities.WeightForAge;
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
public class WeightForAgeCalculator extends AbstractCalculator {

    IZScoreDataSource weightForAgeDataSource;

    @Override
    public IZScoreEntity calculateZScore(Patient patient, Context context) {
        weightForAgeDataSource = new WeightForAgeDataSource(context);
        WeightForAge weightForAge = (WeightForAge) weightForAgeDataSource.getScore(patient.getAgeInWeeks(), patient.getAgeInMonths(), patient.getAgeInYears(), patient.getSex());
        return weightForAge;
    }

    @Override
    public GraphModel getGraphModel(Patient patient, ZScoreGraphTypes zScoreGraphTypes, Context context) {
        weightForAgeDataSource = new WeightForAgeDataSource(context);

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
        List<WeightForAge> scoreRangeForWeight = null;
        int minGraphXAxis = createXMin(patient);
        int maxGraphXAxis = createXMax(patient);

        switch (zScoreGraphTypes){

            case WEIGHT_FOR_AGE_BOYS:
                scoreRangeForWeight = getScoreRange(ageGroup, patient.getSex(), zScoreGraphTypes);
                break;
            case WEIGHT_FOR_AGE_GIRLS:
                scoreRangeForWeight = getScoreRange(ageGroup, patient.getSex(), zScoreGraphTypes);
                break;
        }

        graphModel = createGraphModelForWeight(scoreRangeForWeight, zScoreGraphTypes, age, patient);
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

    private GraphModel createGraphModelForWeight(List<WeightForAge> scoreRangeForWeight, ZScoreGraphTypes zScoreGraphTypes, Age age, Patient patient) {
        GraphModel graphModel = new GraphModel();
        graphModel.setzScoreGraphTypes(zScoreGraphTypes);
        graphModel.setAge(age);
        Double minusThreeScoreDoubleValue = new Double(scoreRangeForWeight.get(0).getMinusThreeScore());
        Double maxThreeScoreDoubleValue = new Double(scoreRangeForWeight.get(scoreRangeForWeight.size() - 1).getThreeScore());
        graphModel.setyMin( minusThreeScoreDoubleValue.intValue() - 10);
        graphModel.setyMax(maxThreeScoreDoubleValue.intValue() + 10);
        for(WeightForAge weightForAge : scoreRangeForWeight){
            graphModel.getMinusThreeScore().add(weightForAge.getMinusThreeScore());
            graphModel.getMinusTwoScore().add(weightForAge.getMinusTwoScore());
            graphModel.getMinusOneScore().add(weightForAge.getMinusOneScore());
            graphModel.getZeroScore().add(weightForAge.getZeroScore());
            graphModel.getOneScore().add(weightForAge.getOneScore());
            graphModel.getTwoScore().add(weightForAge.getTwoScore());
            graphModel.getThreeScore().add(weightForAge.getThreeScore());

        }

        graphModel.setPatientWeight(patient.getWeight());
        return graphModel;
    }

    @Override
    public List getScoreRange(int minWeeks, int maxWeeks, int minMonths, int maxMonths, int minYears, int maxYears, Sex sex, ZScoreGraphTypes zScoreGraphTypes) {
        List<WeightForAge> scoreRangeForWeight = null;
        switch (zScoreGraphTypes){

            case WEIGHT_FOR_AGE_BOYS:
                scoreRangeForWeight = weightForAgeDataSource.getScoreRange(minWeeks, maxWeeks, minMonths, maxMonths, minYears, maxYears, Sex.MALE);
                break;
            case WEIGHT_FOR_AGE_GIRLS:
                scoreRangeForWeight = weightForAgeDataSource.getScoreRange(minWeeks, maxWeeks, minMonths, maxMonths, minYears, maxYears, Sex.FEMALE);
                break;
        }

        return scoreRangeForWeight;
    }

    private int createXMax(Patient patient) {
        if(patient.getAgeInWeeks() > 0 || patient.getAgeInYears() > 4 ){
            return 13;
        }else {
            return 12;
        }
    }

    private int createXMin(Patient patient) {

        return 0;
    }
}

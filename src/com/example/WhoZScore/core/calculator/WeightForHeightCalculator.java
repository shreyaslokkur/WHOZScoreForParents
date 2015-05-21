package com.example.WhoZScore.core.calculator;

import android.content.Context;
import com.example.WhoZScore.data.dao.IZScoreDataSource;
import com.example.WhoZScore.data.dao.WeightForHeightAboveTwoYearsDataSource;
import com.example.WhoZScore.data.dao.WeightForHeightBelowTwoYearsDataSource;
import com.example.WhoZScore.data.entities.IZScoreEntity;
import com.example.WhoZScore.data.entities.WeightForHeight;
import com.example.WhoZScore.enums.Age;
import com.example.WhoZScore.enums.AgeGroup;
import com.example.WhoZScore.enums.Sex;
import com.example.WhoZScore.enums.ZScoreGraphTypes;
import com.example.WhoZScore.model.GraphModel;
import com.example.WhoZScore.model.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/9/15
 * Time: 12:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class WeightForHeightCalculator extends AbstractCalculator  {

    IZScoreDataSource weightForHeightDataSource;
    @Override
    public IZScoreEntity calculateZScore(Patient patient, Context context) {
        if(patient.getAgeInYears() > 2){
            weightForHeightDataSource = new WeightForHeightAboveTwoYearsDataSource(context);
        }else {
            weightForHeightDataSource = new WeightForHeightBelowTwoYearsDataSource(context);
        }

        WeightForHeight weightForHeight = (WeightForHeight) weightForHeightDataSource.getScore(new Double(patient.getHeight()).intValue(), patient.getSex());

        return weightForHeight;
    }

    @Override
    public GraphModel getGraphModel(Patient patient, ZScoreGraphTypes zScoreGraphTypes, Context context) {
        if(patient.getAgeInYears() > 2){
            weightForHeightDataSource = new WeightForHeightAboveTwoYearsDataSource(context);
        }else {
            weightForHeightDataSource = new WeightForHeightBelowTwoYearsDataSource(context);
        }

        GraphModel graphModel = null;



        List<WeightForHeight> scoreRangeForWeightForHeight = null;




        int minGraphXAxis = createXMin(patient);
        int maxGraphXAxis = createXMax(patient);

        List<Integer> xAxis = createXAxis(minGraphXAxis, maxGraphXAxis);
        List<String> xAxisTextLabels = createXTextLabels(minGraphXAxis, maxGraphXAxis);

        switch (zScoreGraphTypes){

            case WEIGHT_FOR_HEIGHT_BOYS:
                scoreRangeForWeightForHeight = getScoreRange(minGraphXAxis, maxGraphXAxis, patient.getSex(), zScoreGraphTypes);
                break;
            case WEIGHT_FOR_HEIGHT_GIRLS:
                scoreRangeForWeightForHeight = getScoreRange(minGraphXAxis, maxGraphXAxis, patient.getSex(), zScoreGraphTypes);
                break;
        }

        graphModel = createGraphModelForWeightForHeight(scoreRangeForWeightForHeight, zScoreGraphTypes, patient);
        graphModel.setxAxis(xAxis);
        graphModel.setxMax(maxGraphXAxis);
        graphModel.setxMin(minGraphXAxis);
        graphModel.setxAxisTextLabels(xAxisTextLabels);
        graphModel.setAgeInWeeks(patient.getAgeInWeeks());
        graphModel.setAgeInMonths(patient.getAgeInMonths());
        graphModel.setAgeInYears(patient.getAgeInYears());


        return graphModel;
    }

    public int createXMax(Patient patient) {
        int height = new Double(patient.getHeight()).intValue();

        if(patient.getAgeInYears() < 2){
            if(height + 5 > 110){
                return 110;
            }else {
                return height + 5;
            }

        } else if (patient.getAgeInYears() > 2){
            if(height + 5 > 120){
                return 120;
            }else {
                return height + 5;
            }
        }
        return 0;
    }

    private int createXMin(Patient patient) {
        int height = new Double(patient.getHeight()).intValue();

        if(patient.getAgeInYears() < 2){
            if(height - 5 < 45){
                return 45;
            }else {
                return height - 5;
            }

        } else if (patient.getAgeInYears() > 2){
            if(height - 5 < 65){
                return 120;
            }else {
                return height - 5;
            }
        }
        return 0;
    }

    private GraphModel createGraphModelForWeightForHeight(List<WeightForHeight> scoreRangeForWeightForHeight, ZScoreGraphTypes zScoreGraphTypes, Patient patient) {
        GraphModel graphModel = new GraphModel();
        graphModel.setzScoreGraphTypes(zScoreGraphTypes);
        Double minusThreeScoreDoubleValue = new Double(scoreRangeForWeightForHeight.get(0).getMinusThreeScore());
        Double maxThreeScoreDoubleValue = new Double(scoreRangeForWeightForHeight.get(scoreRangeForWeightForHeight.size() - 1).getThreeScore());
        graphModel.setyMin( minusThreeScoreDoubleValue.intValue() - 10);
        graphModel.setyMax(maxThreeScoreDoubleValue.intValue() + 10);
        for(WeightForHeight weightForHeight : scoreRangeForWeightForHeight){
            graphModel.getMinusThreeScore().add(weightForHeight.getMinusThreeScore());
            graphModel.getMinusTwoScore().add(weightForHeight.getMinusTwoScore());
            graphModel.getMinusOneScore().add(weightForHeight.getMinusOneScore());
            graphModel.getZeroScore().add(weightForHeight.getZeroScore());
            graphModel.getOneScore().add(weightForHeight.getOneScore());
            graphModel.getTwoScore().add(weightForHeight.getTwoScore());
            graphModel.getThreeScore().add(weightForHeight.getThreeScore());

        }
        graphModel.setPatientHeight(patient.getHeight());
        graphModel.setPatientWeight(patient.getWeight());
        return graphModel;
    }


    @Override
    public List getScoreRange(int minHeight, int maxHeight, Sex sex, ZScoreGraphTypes zScoreGraphTypes) {
        List<WeightForHeight> scoreRangeForWeightForHeight = null;
        switch (zScoreGraphTypes){

            case WEIGHT_FOR_HEIGHT_BOYS:
                scoreRangeForWeightForHeight = weightForHeightDataSource.getScoreRange(minHeight, maxHeight, Sex.MALE);
                break;
            case WEIGHT_FOR_HEIGHT_GIRLS:
                scoreRangeForWeightForHeight = weightForHeightDataSource.getScoreRange(minHeight, maxHeight, Sex.FEMALE);
                break;
        }

        return scoreRangeForWeightForHeight;
    }

    public List<Integer> createXAxis(int minHeight, int maxHeight){
        List<Integer> xAxis = new ArrayList<Integer>();
        for(int i = minHeight; i<=maxHeight; i++){
            xAxis.add(i);
        }
        return xAxis;
    }

    public List<String> createXTextLabels(int minHeight, int maxHeight){
        List<String> xAxis = new ArrayList<String>();
        for(int i = minHeight; i<=maxHeight; i++){
            xAxis.add(String.valueOf(i));
        }
        return xAxis;

    }
}

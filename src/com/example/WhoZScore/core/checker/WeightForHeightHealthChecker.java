package com.example.WhoZScore.core.checker;

import android.content.Context;
import com.example.WhoZScore.R;
import com.example.WhoZScore.data.entities.IZScoreEntity;
import com.example.WhoZScore.data.entities.WeightForHeight;
import com.example.WhoZScore.model.IResult;
import com.example.WhoZScore.model.Patient;
import com.example.WhoZScore.model.WeightForHeightResult;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/10/15
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class WeightForHeightHealthChecker implements IHealthChecker {

    @Override
    public IResult getHealthResult(Patient patient, IZScoreEntity zScoreEntity, Context context) {
        IResult weightForHeightResult;
        weightForHeightResult = calculateWeightForHeightResult(patient, zScoreEntity, context);
        return weightForHeightResult;
    }

    private IResult calculateWeightForHeightResult(Patient patient, IZScoreEntity zScoreEntity, Context context) {
        WeightForHeightResult weightForHeightResult = new WeightForHeightResult();
        WeightForHeight weightForHeight = (WeightForHeight) zScoreEntity;
        double weight = patient.getWeight();
        String message = null;
        String indicatorMessage = null;
        boolean isHealthy;
        if(weight > weightForHeight.getThreeScore() ){
            message = context.getString(R.string.greater_than_three);
            indicatorMessage = context.getString(R.string.weight_for_height_more_than_three);
            isHealthy = false;

        }else if(weight <= weightForHeight.getThreeScore() && weight >= weightForHeight.getTwoScore()){
            message = context.getString(R.string.between_two_and_three);
            indicatorMessage = context.getString(R.string.weight_for_height_more_than_two);
            isHealthy = false;

        }else if(weight < weightForHeight.getTwoScore() && weight >= weightForHeight.getZeroScore()){
            message = context.getString(R.string.between_zero_and_two);
            indicatorMessage = context.getString(R.string.normal_parameters);
            isHealthy = true;

        }else if(weight < weightForHeight.getZeroScore() && weight >= weightForHeight.getMinusTwoScore()){
            message = context.getString(R.string.between_minus_two_and_zero);
            indicatorMessage = context.getString(R.string.normal_parameters);
            isHealthy = true;

        }else if(weight < weightForHeight.getMinusTwoScore() && weight >= weightForHeight.getMinusThreeScore()){
            message = context.getString(R.string.between_minus_three_and_minus_two);
            indicatorMessage = context.getString(R.string.weight_for_height_less_than_minus_two);
            isHealthy = false;
        }else{
            message = context.getString(R.string.lesser_than_minus_three);
            indicatorMessage = context.getString(R.string.weight_for_height_less_than_minus_two);
            isHealthy = false;
        }
        weightForHeightResult.setHealthy(isHealthy);
        weightForHeightResult.setzScoreWeightForHeightMessage(message);
        weightForHeightResult.setHealthyWeightForHeightMessage(indicatorMessage);
        return weightForHeightResult;
    }
}

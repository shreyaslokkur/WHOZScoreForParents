package com.example.WhoZScore.core.checker;

import android.content.Context;
import com.example.WhoZScore.R;
import com.example.WhoZScore.data.entities.IZScoreEntity;
import com.example.WhoZScore.data.entities.WeightForAge;
import com.example.WhoZScore.model.IResult;
import com.example.WhoZScore.model.Patient;
import com.example.WhoZScore.model.WeightForAgeResult;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/10/15
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class WeightForAgeHealthChecker implements IHealthChecker {

    public IResult getHealthResult(Patient patient, IZScoreEntity zScoreEntity, Context context){
        WeightForAgeResult weightForAgeResult;
        weightForAgeResult = calculateWeightResultForAge(patient, zScoreEntity, context);
        return weightForAgeResult;

    }

    private WeightForAgeResult calculateWeightResultForAge(Patient patient, IZScoreEntity zScoreEntity, Context context){
        WeightForAgeResult weightForAgeResult = new WeightForAgeResult();
        WeightForAge weightForAge = (WeightForAge) zScoreEntity;
        double weight = patient.getWeight();
        String message = null;
        String indicatorMessage = null;
        boolean isHealthy;
        if(weight > weightForAge.getThreeScore() ){
            message = context.getString(R.string.greater_than_three);
            indicatorMessage = context.getString(R.string.weight_for_age_more_than_three);
            isHealthy = false;

        }else if(weight <= weightForAge.getThreeScore() && weight >= weightForAge.getTwoScore()){
            message = context.getString(R.string.between_two_and_three);
            indicatorMessage = context.getString(R.string.weight_for_age_more_than_two);
            isHealthy = false;

        }else if(weight < weightForAge.getTwoScore() && weight >= weightForAge.getZeroScore()){
            message = context.getString(R.string.between_zero_and_two);
            indicatorMessage = context.getString(R.string.normal_parameters);
            isHealthy = true;

        }else if(weight < weightForAge.getZeroScore() && weight >= weightForAge.getMinusTwoScore()){
            message = context.getString(R.string.between_minus_two_and_zero);
            indicatorMessage = context.getString(R.string.normal_parameters);
            isHealthy = true;

        }else if(weight < weightForAge.getMinusTwoScore() && weight >= weightForAge.getMinusThreeScore()){
            message = context.getString(R.string.between_minus_three_and_minus_two);
            indicatorMessage = context.getString(R.string.weight_for_age_less_than_minus_two);
            isHealthy = false;
        }else{
            message = context.getString(R.string.lesser_than_minus_three);
            indicatorMessage = context.getString(R.string.weight_for_age_less_than_minus_three);
            isHealthy = false;
        }
        weightForAgeResult.setHealthy(isHealthy);
        weightForAgeResult.setzScoreWeightMessage(message);
        weightForAgeResult.setHealthyWeightMessage(indicatorMessage);
        return weightForAgeResult;
    }

}

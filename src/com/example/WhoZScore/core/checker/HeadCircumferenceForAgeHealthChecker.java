package com.example.WhoZScore.core.checker;

import android.content.Context;
import com.example.WhoZScore.R;
import com.example.WhoZScore.data.entities.HeadCircumferenceForAge;
import com.example.WhoZScore.data.entities.IZScoreEntity;
import com.example.WhoZScore.model.HeadCircumferenceForAgeResult;
import com.example.WhoZScore.model.IResult;
import com.example.WhoZScore.model.Patient;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/10/15
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeadCircumferenceForAgeHealthChecker implements IHealthChecker {



    public IResult getHealthResult(Patient patient, IZScoreEntity zScoreEntity, Context context){
        IResult headCircumferenceForAgeResult;
        headCircumferenceForAgeResult = calculateHeadCircumferenceResultForAge(patient, zScoreEntity, context);
        return headCircumferenceForAgeResult;
    }

    private IResult calculateHeadCircumferenceResultForAge(Patient patient, IZScoreEntity zScoreEntity, Context context) {
        HeadCircumferenceForAgeResult headCircumferenceForAgeResult = new HeadCircumferenceForAgeResult();
        HeadCircumferenceForAge headCircumferenceForAge = (HeadCircumferenceForAge) zScoreEntity;
        double headCircumference = patient.getHeadCircumference();
        String message = null;
        String indicatorMessage = null;
        boolean isHealthy;
        if(headCircumference > headCircumferenceForAge.getThreeScore() ){
            message = context.getString(R.string.greater_than_three);
            isHealthy = false;

        }else if(headCircumference <= headCircumferenceForAge.getThreeScore() && headCircumference >= headCircumferenceForAge.getTwoScore()){
            message = context.getString(R.string.between_two_and_three);
            isHealthy = false;

        }else if(headCircumference < headCircumferenceForAge.getTwoScore() && headCircumference >= headCircumferenceForAge.getOneScore()){
            message = context.getString(R.string.between_one_and_two);
            isHealthy = true;

        }else if(headCircumference < headCircumferenceForAge.getOneScore() && headCircumference >= headCircumferenceForAge.getZeroScore()){
            message = context.getString(R.string.between_zero_and_one);
            isHealthy = true;

        }else if(headCircumference < headCircumferenceForAge.getZeroScore() && headCircumference >= headCircumferenceForAge.getMinusOneScore()){
            message = context.getString(R.string.between_minus_one_and_zero);
            isHealthy = true;

        }else if(headCircumference < headCircumferenceForAge.getMinusOneScore() && headCircumference >= headCircumferenceForAge.getMinusTwoScore()){
            message = context.getString(R.string.between_minus_two_and_minus_one);
            isHealthy = true;

        }else if(headCircumference < headCircumferenceForAge.getMinusTwoScore() && headCircumference >= headCircumferenceForAge.getMinusThreeScore()){
            message = context.getString(R.string.between_minus_three_and_minus_two);
            isHealthy = false;
        }else{
            message = context.getString(R.string.lesser_than_minus_three);
            isHealthy = false;
        }
        headCircumferenceForAgeResult.setHealthy(isHealthy);
        headCircumferenceForAgeResult.setzScoreHeadCircumferenceMessage(message);
        return headCircumferenceForAgeResult;
    }

}

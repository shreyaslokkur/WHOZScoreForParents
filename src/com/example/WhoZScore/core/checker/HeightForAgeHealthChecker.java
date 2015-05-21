package com.example.WhoZScore.core.checker;

import android.content.Context;
import com.example.WhoZScore.R;
import com.example.WhoZScore.WhoZScore;
import com.example.WhoZScore.data.entities.HeightForAge;
import com.example.WhoZScore.data.entities.IZScoreEntity;
import com.example.WhoZScore.model.HeightForAgeResult;
import com.example.WhoZScore.model.IResult;
import com.example.WhoZScore.model.Patient;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/10/15
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeightForAgeHealthChecker implements IHealthChecker {



    public IResult getHealthResult(Patient patient, IZScoreEntity zScoreEntity, Context context){
        IResult heightForAgeResult;
        heightForAgeResult = calculateHeightResultForAge(patient, zScoreEntity, context);
        return heightForAgeResult;
    }

    private IResult calculateHeightResultForAge(Patient patient, IZScoreEntity zScoreEntity, Context context) {
        HeightForAgeResult heightForAgeResult = new HeightForAgeResult();
        HeightForAge heightForAge = (HeightForAge) zScoreEntity;
        double height = patient.getHeight();
        String zScoreMessage = null;
        String indicatorMessage = null;
        boolean isHealthy;
        if(height > heightForAge.getThreeScore() ){
            zScoreMessage = context.getString(R.string.greater_than_three);
            indicatorMessage = context.getString(R.string.height_for_age_more_than_three);
            isHealthy = false;

        }else if(height <= heightForAge.getThreeScore() && height >= heightForAge.getTwoScore()){
            zScoreMessage = context.getString(R.string.between_two_and_three);
            indicatorMessage = context.getString(R.string.height_for_age_more_than_two);
            isHealthy = false;

        }else if(height < heightForAge.getTwoScore() && height >= heightForAge.getZeroScore()){
            zScoreMessage = context.getString(R.string.between_zero_and_two);
            indicatorMessage = context.getString(R.string.normal_parameters);
            isHealthy = true;

        }else if(height < heightForAge.getZeroScore() && height >= heightForAge.getMinusTwoScore()){
            zScoreMessage = context.getString(R.string.between_minus_two_and_zero);
            indicatorMessage = context.getString(R.string.normal_parameters);
            isHealthy = true;

        }else if(height < heightForAge.getMinusTwoScore() && height >= heightForAge.getMinusThreeScore()){
            zScoreMessage = context.getString(R.string.between_minus_three_and_minus_two);
            indicatorMessage = context.getString(R.string.height_for_age_less_than_minus_two);
            isHealthy = false;
        }else{
            zScoreMessage = context.getString(R.string.lesser_than_minus_three);
            indicatorMessage = context.getString(R.string.height_for_age_less_than_minus_three);
            isHealthy = false;
        }
        heightForAgeResult.setHealthy(isHealthy);
        heightForAgeResult.setzScoreHeightMessage(zScoreMessage);
        heightForAgeResult.setHealthyHeightMessage(indicatorMessage);
        return heightForAgeResult;
    }

}

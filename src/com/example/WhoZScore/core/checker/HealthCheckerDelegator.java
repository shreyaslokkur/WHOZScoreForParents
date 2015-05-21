package com.example.WhoZScore.core.checker;

import android.content.Context;
import com.example.WhoZScore.data.entities.HeadCircumferenceForAge;
import com.example.WhoZScore.data.entities.HeightForAge;
import com.example.WhoZScore.data.entities.WeightForAge;
import com.example.WhoZScore.data.entities.WeightForHeight;
import com.example.WhoZScore.model.*;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/29/15
 * Time: 3:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class HealthCheckerDelegator {

    HeightForAgeHealthChecker heightForAgeHealthChecker = new HeightForAgeHealthChecker();
    WeightForAgeHealthChecker weightForAgeHealthChecker = new WeightForAgeHealthChecker();
    WeightForHeightHealthChecker weightForHeightHealthChecker = new WeightForHeightHealthChecker();
    HeadCircumferenceForAgeHealthChecker headCircumferenceForAgeHealthChecker = new HeadCircumferenceForAgeHealthChecker();

    public Result getHealthResult(Patient patient, WeightForAge weightForAge, HeightForAge heightForAge, WeightForHeight weightForHeight, HeadCircumferenceForAge headCircumferenceForAge, Context context){
        Result result = null;
        WeightForAgeResult resultForWeight = null;
        HeightForAgeResult resultForHeight = null;
        WeightForHeightResult weightForHeightResult = null;
        HeadCircumferenceForAgeResult headCircumferenceForAgeResult = null;
        if(weightForAge != null){
           resultForWeight = (WeightForAgeResult) weightForAgeHealthChecker.getHealthResult(patient, weightForAge, context);
        }
        if(heightForAge != null){
            resultForHeight = (HeightForAgeResult) heightForAgeHealthChecker.getHealthResult(patient, heightForAge, context);
        }
        if(weightForHeight != null){
           weightForHeightResult = (WeightForHeightResult) weightForHeightHealthChecker.getHealthResult(patient, weightForHeight, context);
        }
        if(headCircumferenceForAge != null){
            headCircumferenceForAgeResult = (HeadCircumferenceForAgeResult) headCircumferenceForAgeHealthChecker.getHealthResult(patient, headCircumferenceForAge, context);
        }

        result = setResult(resultForWeight, resultForHeight, weightForHeightResult, headCircumferenceForAgeResult);
        return result;
    }


    private Result setResult(WeightForAgeResult weightForAgeResult, HeightForAgeResult heightForAgeResult, WeightForHeightResult weightForHeightResult, HeadCircumferenceForAgeResult headCircumferenceForAgeResult){
        Result result = new Result();
        if(weightForAgeResult != null){
            result.setzScoreWeightForAgeMessage(weightForAgeResult.getzScoreWeightMessage());
            result.setHealthyWeightForAgeMessage(weightForAgeResult.getHealthyWeightMessage());
        }
        if(heightForAgeResult != null){
            result.setzScoreHeightForAgeMessage(heightForAgeResult.getzScoreHeightMessage());
            result.setHealthyHeightForAgeMessage(heightForAgeResult.getHealthyHeightMessage());
        }
        if(weightForHeightResult != null){
            result.setzScoreWeightForHeightMessage(weightForHeightResult.getzScoreWeightForHeightMessage());
            result.setHealthyWeightForHeightMessage(weightForHeightResult.getHealthyWeightForHeightMessage());
        }
        if(headCircumferenceForAgeResult != null){
            result.setzScoreHeadCircumferenceForAgeMessage(headCircumferenceForAgeResult.getzScoreHeadCircumferenceMessage());
        }
        return result;
    }
}

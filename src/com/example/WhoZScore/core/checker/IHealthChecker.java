package com.example.WhoZScore.core.checker;

import android.content.Context;
import com.example.WhoZScore.data.entities.IZScoreEntity;
import com.example.WhoZScore.model.IResult;
import com.example.WhoZScore.model.Patient;
import com.example.WhoZScore.model.Result;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/10/15
 * Time: 5:54 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IHealthChecker {

    IResult getHealthResult(Patient patient, IZScoreEntity zScoreEntity, Context context);
}

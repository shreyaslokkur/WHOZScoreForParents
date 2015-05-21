package com.example.WhoZScore.core.calculator;

import android.content.Context;
import com.example.WhoZScore.data.entities.IZScoreEntity;
import com.example.WhoZScore.enums.ZScoreGraphTypes;
import com.example.WhoZScore.model.GraphModel;
import com.example.WhoZScore.model.Patient;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/9/15
 * Time: 12:09 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ICalculator {

     IZScoreEntity calculateZScore(Patient patient, Context context);
     GraphModel getGraphModel(Patient patient, ZScoreGraphTypes zScoreGraphTypes, Context context);
}

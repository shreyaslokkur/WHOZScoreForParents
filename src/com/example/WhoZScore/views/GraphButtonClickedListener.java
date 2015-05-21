package com.example.WhoZScore.views;

import android.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageButton;
import android.widget.Spinner;
import com.example.WhoZScore.R;
import com.example.WhoZScore.WhoZScore;
import com.example.WhoZScore.enums.Age;
import com.example.WhoZScore.enums.Sex;
import com.example.WhoZScore.enums.ZScoreGraphTypes;
import com.example.WhoZScore.model.Patient;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/29/15
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class GraphButtonClickedListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {

        ImageButton button = (ImageButton)v;
        WhoZScore whoZScore = (WhoZScore) v.getContext();
        Patient patient = whoZScore.getPatient();
        final Fragment graphView = new GraphView();
        if(button.getId() == R.id.weightForAgeGraph){
            if(Sex.FEMALE.equals(patient.getSex()))
                ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.WEIGHT_FOR_AGE_GIRLS);
            else
                ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.WEIGHT_FOR_AGE_BOYS);

        }else if(button.getId() == R.id.heightForAgeGraph){
            if(Sex.FEMALE.equals(patient.getSex()))
                ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.HEIGHT_FOR_AGE_GIRLS);
            else
                ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.HEIGHT_FOR_AGE_BOYS);

        }else if(button.getId() == R.id.weightForHeightGraph){
            if(Sex.FEMALE.equals(patient.getSex()))
                ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.WEIGHT_FOR_HEIGHT_GIRLS);
            else
                ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.WEIGHT_FOR_HEIGHT_BOYS);

        }else if(button.getId() == R.id.headCircumferenceForAgeGraph){
            if(Sex.FEMALE.equals(patient.getSex()))
                ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.HEAD_CIRCUMFERENCE_FOR_AGE_GIRLS);
            else
                ((GraphView)graphView).setScoreGraphTypes(ZScoreGraphTypes.HEAD_CIRCUMFERENCE_FOR_AGE_BOYS);

        }


        whoZScore.replaceFragment(graphView);




    }
}

package com.example.WhoZScore.views;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.WhoZScore.R;
import com.example.WhoZScore.WhoZScore;
import com.example.WhoZScore.enums.Sex;
import com.example.WhoZScore.enums.ZScoreGraphTypes;
import com.example.WhoZScore.model.Patient;
import com.example.WhoZScore.model.Result;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 8/18/14
 * Time: 3:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResultView extends Fragment {

    private TextView ageText, weightText, heightText, headCircumferenceText, zScoreWeightMessageText, zScoreHeightMessageText, zScoreWeightForHeightMessageText, zScoreHeadCircumferenceForAgeMessageText, weightHeader, heightHeader, weightForHeightHeader, headCircumferenceHeader;

    private LinearLayout heightResultLayout, weightForHeightLayout, weightResultLayout, headCircumferenceResultLayout;

    private LinearLayout heightHeaderLayout, weightHeaderLayout, weightForHeightHeaderLayout, headCircumferenceHeaderLayout;

    private ImageButton weightForAgeGraph, heightForAgeGraph, weightForHeightGraph, headCircumferenceForAgeGraph;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.result_view, container, false);
        final Fragment graphView = new GraphView();
        ageText = (TextView) view.findViewById(R.id.ageResultTextId);
        weightText = (TextView) view.findViewById(R.id.weightResultTextId);
        heightText = (TextView) view.findViewById(R.id.heightResultTextId);
        headCircumferenceText = (TextView) view.findViewById(R.id.headCircumferenceResultTextId);
        weightHeader = (TextView) view.findViewById(R.id.weightHeader);
        heightHeader = (TextView) view.findViewById(R.id.heightHeader);
        headCircumferenceHeader = (TextView) view.findViewById(R.id.headCircumferenceForAgeHeader);
        zScoreWeightMessageText = (TextView) view.findViewById(R.id.zScoreWeightResultTextId);
        zScoreHeightMessageText = (TextView) view.findViewById(R.id.zScoreHeightResultTextId);
        zScoreWeightForHeightMessageText = (TextView) view.findViewById(R.id.zScoreWeightForHeightResultTextId);
        zScoreHeadCircumferenceForAgeMessageText = (TextView) view.findViewById(R.id.zScoreHeadCircumferenceForAgeResultTextId);
        weightResultLayout = (LinearLayout) view.findViewById(R.id.weightResultLayout);
        heightResultLayout = (LinearLayout) view.findViewById(R.id.heightResultLayout);
        weightForHeightHeader = (TextView) view.findViewById(R.id.weightForHeightHeader);
        weightForHeightLayout = (LinearLayout) view.findViewById(R.id.weightForHeightResultLayout);
        headCircumferenceResultLayout = (LinearLayout) view.findViewById(R.id.headCircumferenceForAgeResultLayout);

        heightHeaderLayout = (LinearLayout) view.findViewById(R.id.heightHeaderLayout);
        weightHeaderLayout = (LinearLayout) view.findViewById(R.id.weightHeaderLayout);
        weightForHeightHeaderLayout = (LinearLayout) view.findViewById(R.id.weightForHeightHeaderLayout);
        headCircumferenceHeaderLayout = (LinearLayout) view.findViewById(R.id.headCircumferenceForAgeHeaderLayout);

        weightForAgeGraph = (ImageButton) view.findViewById(R.id.weightForAgeGraph);
        heightForAgeGraph = (ImageButton) view.findViewById(R.id.heightForAgeGraph);
        weightForHeightGraph = (ImageButton) view.findViewById(R.id.weightForHeightGraph);
        headCircumferenceForAgeGraph = (ImageButton) view.findViewById(R.id.headCircumferenceForAgeGraph);

        Result result = ((WhoZScore) getActivity()).getResult();
        final Patient patient = ((WhoZScore) getActivity()).getPatient();
        if(result.getzScoreHeightForAgeMessage() == null){

            ((LinearLayout)heightHeaderLayout.getParent()).removeView(heightHeaderLayout);
            ((LinearLayout)weightForHeightHeaderLayout.getParent()).removeView(weightForHeightHeaderLayout);
            ((LinearLayout)heightResultLayout.getParent()).removeView(heightResultLayout);
            ((LinearLayout)weightForHeightLayout.getParent()).removeView(weightForHeightLayout);
        }else {
            heightText.setText(String.valueOf(patient.getHeight()) + "cms");
            heightText.setTextColor(Color.BLACK);
            zScoreHeightMessageText.setText(setMessage(result.getzScoreHeightForAgeMessage(), result.getHealthyHeightForAgeMessage()));
        }

        if(result.getHealthyWeightForAgeMessage() == null){

            ((LinearLayout)weightHeaderLayout.getParent()).removeView(weightHeaderLayout);
            ((LinearLayout)weightForHeightHeaderLayout.getParent()).removeView(weightForHeightHeaderLayout);
            ((LinearLayout)weightResultLayout.getParent()).removeView(weightResultLayout);
            ((LinearLayout)weightForHeightLayout.getParent()).removeView(weightForHeightLayout);
        }else {
            weightText.setText(String.valueOf(patient.getWeight()) + "kg");
            weightText.setTextColor(Color.BLACK);
            zScoreWeightMessageText.setText(setMessage(result.getzScoreWeightForAgeMessage(), result.getHealthyWeightForAgeMessage()));
        }

        if(result.getzScoreWeightForHeightMessage() != null){
            zScoreWeightForHeightMessageText.setText(setMessage(result.getzScoreWeightForHeightMessage(), result.getHealthyWeightForHeightMessage()));
        }else if(result.getzScoreHeightForAgeMessage() != null && result.getzScoreWeightForAgeMessage() != null){
            zScoreWeightForHeightMessageText.setText(setMessage("Unable to calculate Weight For Height for the given values", null));
            weightForHeightGraph.setEnabled(false);
        }

        if(result.getzScoreHeadCircumferenceForAgeMessage() == null){


            ((LinearLayout)headCircumferenceHeaderLayout.getParent()).removeView(headCircumferenceHeaderLayout);

            ((LinearLayout)headCircumferenceResultLayout.getParent()).removeView(headCircumferenceResultLayout);


        }else {
            headCircumferenceText.setText(String.valueOf(patient.getHeadCircumference()) + "cms");
            headCircumferenceText.setTextColor(Color.BLACK);
            zScoreHeadCircumferenceForAgeMessageText.setText(result.getzScoreHeadCircumferenceForAgeMessage());
        }




        ageText.setText(getAge(patient));



        weightForHeightGraph.setOnClickListener(new GraphButtonClickedListener());
        weightForAgeGraph.setOnClickListener(new GraphButtonClickedListener());
        heightForAgeGraph.setOnClickListener(new GraphButtonClickedListener());
        headCircumferenceForAgeGraph.setOnClickListener(new GraphButtonClickedListener());

        // Inflate the layout for this fragment
        return view;

    }

    private String getAge(Patient patient){
        String age = patient.getDisplayAgeInYears() + " years, "+patient.getDisplayAgeInMonths()+" months and "+patient.getDisplayAgeInWeeks() +" weeks";
        return age;
    }

    private String setMessage(String zScore, String healthyMessage){
        StringBuilder message = new StringBuilder(zScore);
        /*message.append(" ( ").append(healthyMessage).append(" )");*/

        return message.toString();
    }



    @Override
    public String toString(){
        return "ResultView";
    }
}

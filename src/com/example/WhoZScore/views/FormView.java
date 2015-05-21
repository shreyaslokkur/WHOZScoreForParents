package com.example.WhoZScore.views;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.WhoZScore.R;
import com.example.WhoZScore.WhoZScore;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 8/18/14
 * Time: 3:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormView extends Fragment {

    private int ageInYears = 0;
    private int ageInMonths = 0;
    private int ageInWeeks = 0;
    private double weight;

    private Spinner yearsSpinner, monthsSpinner, weeksSpinner;
    private Button submitButton;
    private EditText weightText;
    private EditText heightText;
    private EditText headCircumferenceText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.form_view, container, false);
        final Fragment resultView = new ResultView();
        yearsSpinner = (Spinner) view.findViewById(R.id.yearsId);
        yearsSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        monthsSpinner = (Spinner) view.findViewById(R.id.monthsId);
        monthsSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        weeksSpinner = (Spinner) view.findViewById(R.id.weeksId);
        weeksSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        weightText = (EditText) view.findViewById(R.id.weightId);

        submitButton = (Button) view.findViewById(R.id.submitButton);

        heightText = (EditText) view.findViewById(R.id.heightId);

        headCircumferenceText = (EditText) view.findViewById(R.id.headCircumferenceId);

        weightText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()> 0){
                    ((WhoZScore)getActivity()).setPatientWeight(Double.parseDouble(weightText.getText().toString()));
                    toggleSubmitButton();
                }else {
                    ((WhoZScore)getActivity()).setPatientWeight(0.0);
                    toggleSubmitButton();
                }
            }
        });

        heightText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()> 0){
                    ((WhoZScore)getActivity()).setPatientHeight(Double.parseDouble(heightText.getText().toString()));
                    toggleSubmitButton();
                }else {
                    ((WhoZScore)getActivity()).setPatientHeight(0.0);
                    toggleSubmitButton();
                }
            }
        });

        headCircumferenceText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()> 0){
                    ((WhoZScore)getActivity()).setPatientHeadCircumference(Double.parseDouble(headCircumferenceText.getText().toString()));
                    toggleSubmitButton();
                }else {
                    ((WhoZScore)getActivity()).setPatientHeadCircumference(0.0);
                    toggleSubmitButton();
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("valuesSubmitted.............................");

                ((WhoZScore)getActivity()).onFormSubmit();

                ((WhoZScore)getActivity()).replaceFragment(resultView);
            }
        });


        // Inflate the layout for this fragment
        return view;

    }

    public void toggleSubmitButton(){
        if(weightText.getText().length() > 0 || heightText.getText().length() > 0){
            submitButton.setEnabled(true);
        }else {
            submitButton.setEnabled(false);
        }
    }

    @Override
    public String toString(){
        return "FormView";
    }
}

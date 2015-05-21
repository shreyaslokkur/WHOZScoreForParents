package com.example.WhoZScore.views;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import com.example.WhoZScore.R;
import com.example.WhoZScore.WhoZScore;
import com.example.WhoZScore.enums.Age;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/29/15
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomOnItemSelectedListener implements OnItemSelectedListener  {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        WhoZScore whoZScore = (WhoZScore) parent.getContext();
        if(parent.getSelectedItem()!= null){
            if(spinner.getId() == R.id.weeksId){
                whoZScore.setPatientAgeInWeeks(Integer.parseInt((String) parent.getSelectedItem()));
            }else if(spinner.getId() == R.id.monthsId){
                int months = Integer.parseInt((String) parent.getSelectedItem());
                whoZScore.setPatientAgeInMonths(months);
                if(months > 0){
                    whoZScore.toggleSpinners(Age.WEEKS, false);
                }else if(months <=3 && whoZScore.getPatient().getAgeInYears() > 0) {
                    whoZScore.toggleSpinners(Age.WEEKS, false);
                }else {
                    whoZScore.toggleSpinners(Age.WEEKS, true);
                }
            }else if(spinner.getId() == R.id.yearsId){
                int years = Integer.parseInt((String) parent.getSelectedItem());
                whoZScore.setPatientAgeInYears(years);
                if(years == 5){
                    whoZScore.toggleSpinners(Age.MONTHS, false);
                    whoZScore.toggleSpinners(Age.WEEKS, false);
                }
                else if(years > 0){
                    whoZScore.toggleSpinners(Age.MONTHS, true);
                    whoZScore.toggleSpinners(Age.WEEKS, false);
                }else {
                    whoZScore.toggleSpinners(Age.MONTHS, true);
                    whoZScore.toggleSpinners(Age.WEEKS, true);
                }
            }

        }



    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

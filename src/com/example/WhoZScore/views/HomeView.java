package com.example.WhoZScore.views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.example.WhoZScore.R;
import com.example.WhoZScore.WhoZScore;
import com.example.WhoZScore.data.dao.WeightForAgeDataSource;
import com.example.WhoZScore.enums.Sex;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 8/19/14
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class HomeView extends Fragment {

    ImageButton boyButton ;
    ImageButton girlButton;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.home_view, container, false);
        final Fragment formView = new FormView();
        boyButton = (ImageButton) view.findViewById(R.id.boy_image);
        boyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("boy.............................");
                ((WhoZScore)getActivity()).setPatientGender(Sex.MALE);
                ((WhoZScore)getActivity()).replaceFragment(formView);
            }
        });

        girlButton = (ImageButton)view.findViewById(R.id.girl_image);
        girlButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                System.out.println("girl............................");
                ((WhoZScore)getActivity()).setPatientGender(Sex.FEMALE);
                ((WhoZScore)getActivity()).replaceFragment(formView);
            }
        });
        return view;
    }

    @Override
    public String toString(){
        return "HomeView";
    }


}

package com.example.WhoZScore.data.dao;

import android.content.Context;
import android.database.Cursor;
import com.example.WhoZScore.data.MySqliteHelper;
import com.example.WhoZScore.data.entities.IZScoreEntity;
import com.example.WhoZScore.data.entities.WeightForHeight;
import com.example.WhoZScore.enums.Sex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/26/15
 * Time: 6:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class WeightForHeightAboveTwoYearsDataSource extends AbstractZScoreDataSource {


    private MySqliteHelper dbHelper;

    public static final String BOYS_WEIGHT_FOR_HEIGHT = "BoysWeightForHeightAboveTwoYears";
    public static final String GIRLS_WEIGHT_FOR_HEIGHT = "GirlsWeightForHeightAboveTwoYears";


    private String[] scoreColumns = { COLUMN_MINUS_THREE_SCORE,COLUMN_MINUS_TWO_SCORE,COLUMN_MINUS_ONE_SCORE,COLUMN_ZERO_SCORE,COLUMN_ONE_SCORE,COLUMN_TWO_SCORE,COLUMN_THREE_SCORE };

    public WeightForHeightAboveTwoYearsDataSource(Context context) {

        try {
            dbHelper = new MySqliteHelper(context);
            dbHelper.createDataBase();
            dbHelper.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


    private WeightForHeight getScoreForBoys(int height) {
        WeightForHeight weightForHeight = null;
        String whereClaue = COLUMN_HEIGHT + "=?"  ;
        String[] whereParameters = new String[]{String.valueOf(height)};


        Cursor cursor = dbHelper.myDataBase.query(BOYS_WEIGHT_FOR_HEIGHT,
                scoreColumns, whereClaue, whereParameters, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            weightForHeight = cursorToWeightForHeight(cursor);
            cursor.moveToNext();

        }


        // make sure to close the cursor
        cursor.close();
        return weightForHeight;
    }

    public IZScoreEntity getScore(int height, Sex sex){

        if(Sex.FEMALE.equals(sex)){
            return getScoreForGirls(height);
        }else {
            return getScoreForBoys(height);
        }



    }



    private WeightForHeight getScoreForGirls(int height) {
        WeightForHeight weightForHeight = null;
        String whereClaue = COLUMN_HEIGHT + "=?"  ;
        String[] whereParameters = new String[]{String.valueOf(height)};


        Cursor cursor = dbHelper.myDataBase.query(GIRLS_WEIGHT_FOR_HEIGHT,
                scoreColumns, whereClaue, whereParameters, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            weightForHeight = cursorToWeightForHeight(cursor);
            cursor.moveToNext();

        }


        // make sure to close the cursor
        cursor.close();
        return weightForHeight;
    }

    public List<WeightForHeight> getScoreRange(int minHeight, int maxHeight, Sex sex){
        List<WeightForHeight> weightForHeightList = new ArrayList<WeightForHeight>();
        String whereClause = COLUMN_HEIGHT + " BETWEEN " + "?" + "AND " + "?" ;

        String[] whereParameters = new String[]{String.valueOf(minHeight),String.valueOf(maxHeight)};

        String tableName;
        if(Sex.MALE.equals(sex)){
            tableName = BOYS_WEIGHT_FOR_HEIGHT;
        }else {
            tableName = GIRLS_WEIGHT_FOR_HEIGHT;
        }

        Cursor cursor = dbHelper.myDataBase.query(tableName,
                scoreColumns, whereClause, whereParameters, null, null, null);

        cursor.moveToFirst();
        WeightForHeight weightForHeight = null;
        while (!cursor.isAfterLast()) {
            weightForHeight = cursorToWeightForHeight(cursor);
            weightForHeightList.add(weightForHeight);
            cursor.moveToNext();

        }


        // make sure to close the cursor
        cursor.close();
        return weightForHeightList;

    }

    private WeightForHeight cursorToWeightForHeight(Cursor cursor) {
        WeightForHeight weightForHeight = new WeightForHeight();
        weightForHeight.setThreeScore(cursor.getDouble(cursor.getColumnIndex(COLUMN_THREE_SCORE)));
        weightForHeight.setTwoScore(cursor.getDouble(cursor.getColumnIndex(COLUMN_TWO_SCORE)));
        weightForHeight.setOneScore(cursor.getDouble(cursor.getColumnIndex(COLUMN_ONE_SCORE)));
        weightForHeight.setZeroScore(cursor.getDouble(cursor.getColumnIndex(COLUMN_ZERO_SCORE)));
        weightForHeight.setMinusOneScore(cursor.getDouble(cursor.getColumnIndex(COLUMN_MINUS_ONE_SCORE)));
        weightForHeight.setMinusTwoScore(cursor.getDouble(cursor.getColumnIndex(COLUMN_MINUS_TWO_SCORE)));
        weightForHeight.setMinusThreeScore(cursor.getDouble(cursor.getColumnIndex(COLUMN_MINUS_THREE_SCORE)));
        return weightForHeight;
    }
}

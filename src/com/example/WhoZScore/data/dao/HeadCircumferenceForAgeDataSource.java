package com.example.WhoZScore.data.dao;

import android.content.Context;
import android.database.Cursor;
import com.example.WhoZScore.data.MySqliteHelper;
import com.example.WhoZScore.data.entities.HeadCircumferenceForAge;
import com.example.WhoZScore.data.entities.HeightForAge;
import com.example.WhoZScore.data.entities.IZScoreEntity;
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
public class HeadCircumferenceForAgeDataSource extends AbstractZScoreDataSource {


    private MySqliteHelper dbHelper;

    public static final String BOYS_HEAD_CIRCUMFERENCE_FOR_AGE = "BoysHeadCircumferenceForAge";
    public static final String GIRLS_HEAD_CIRCUMFERENCE_FOR_AGE = "GirlsHeadCircumferenceForAge";




    private String[] scoreColumns = { COLUMN_MINUS_THREE_SCORE,COLUMN_MINUS_TWO_SCORE,COLUMN_MINUS_ONE_SCORE,COLUMN_ZERO_SCORE,COLUMN_ONE_SCORE,COLUMN_TWO_SCORE,COLUMN_THREE_SCORE };

    public HeadCircumferenceForAgeDataSource(Context context) {

        try {
            dbHelper = new MySqliteHelper(context);
            dbHelper.createDataBase();
            dbHelper.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


    private HeadCircumferenceForAge getScoreForBoys(int weeks, int months, int years) {
        HeadCircumferenceForAge headCircumferenceForAge = null;
        String whereClaue = COLUMN_WEEKS + "=?" + " and " + COLUMN_MONTHS + "=?" + " and " + COLUMN_YEARS + "=?" ;
        String[] whereParameters = new String[]{String.valueOf(weeks),String.valueOf(months),String.valueOf(years)};


        Cursor cursor = dbHelper.myDataBase.query(BOYS_HEAD_CIRCUMFERENCE_FOR_AGE,
                scoreColumns, whereClaue, whereParameters, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            headCircumferenceForAge = cursorToHeadCircumferenceForAge(cursor);
            cursor.moveToNext();

        }


        // make sure to close the cursor
        cursor.close();
        return headCircumferenceForAge;
    }

    public IZScoreEntity getScore(int weeks, int months, int years, Sex sex){

            if(Sex.FEMALE.equals(sex)){
                return getScoreForGirls(weeks,months,years);
            }else {
                return getScoreForBoys(weeks,months,years);
            }



    }



    private HeadCircumferenceForAge getScoreForGirls(int weeks, int months, int years) {
        HeadCircumferenceForAge headCircumferenceForAge = null;
        String whereClaue = COLUMN_WEEKS + "=?" + " and " + COLUMN_MONTHS + "=?" + " and " + COLUMN_YEARS + "=?" ;
        String[] whereParameters = new String[]{String.valueOf(weeks),String.valueOf(months),String.valueOf(years)};


        Cursor cursor = dbHelper.myDataBase.query(GIRLS_HEAD_CIRCUMFERENCE_FOR_AGE,
                scoreColumns, whereClaue, whereParameters, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            headCircumferenceForAge = cursorToHeadCircumferenceForAge(cursor);
            cursor.moveToNext();

        }


        // make sure to close the cursor
        cursor.close();
        return headCircumferenceForAge;
    }

    public List<HeadCircumferenceForAge> getScoreRange(int minWeeks, int maxWeeks, int minMonths, int maxMonths, int minYears, int maxYears, Sex sex){
        List<HeadCircumferenceForAge> headCircumferenceForAgeList = new ArrayList<HeadCircumferenceForAge>();
        String whereClause = COLUMN_WEEKS + " BETWEEN " + "?" + "AND " + "?" + " AND " + COLUMN_MONTHS + " BETWEEN " + "?" + "AND " + "?" + " AND " + COLUMN_YEARS + " BETWEEN " + "?" + "AND " + "?" ;

        String[] whereParameters = new String[]{String.valueOf(minWeeks),String.valueOf(maxWeeks),String.valueOf(minMonths),String.valueOf(maxMonths),String.valueOf(minYears), String.valueOf(maxYears)};

        String tableName;
        if(Sex.MALE.equals(sex)){
            tableName = BOYS_HEAD_CIRCUMFERENCE_FOR_AGE;
        }else {
            tableName = GIRLS_HEAD_CIRCUMFERENCE_FOR_AGE;
        }

        Cursor cursor = dbHelper.myDataBase.query(tableName,
                scoreColumns, whereClause, whereParameters, null, null, null);

        cursor.moveToFirst();
        HeadCircumferenceForAge headCircumferenceForAge = null;
        while (!cursor.isAfterLast()) {
            headCircumferenceForAge = cursorToHeadCircumferenceForAge(cursor);
            headCircumferenceForAgeList.add(headCircumferenceForAge);
            cursor.moveToNext();

        }


        // make sure to close the cursor
        cursor.close();
        return headCircumferenceForAgeList;

    }

    private HeadCircumferenceForAge cursorToHeadCircumferenceForAge(Cursor cursor) {
        HeadCircumferenceForAge headCircumferenceForAge = new HeadCircumferenceForAge();
        headCircumferenceForAge.setThreeScore(cursor.getDouble(THREE_SCORE_COLUMN_INDEX));
        headCircumferenceForAge.setTwoScore(cursor.getDouble(TWO_SCORE_COLUMN_INDEX));
        headCircumferenceForAge.setOneScore(cursor.getDouble(ONE_SCORE_COLUMN_INDEX));
        headCircumferenceForAge.setZeroScore(cursor.getDouble(ZERO_SCORE_COLUMN_INDEX));
        headCircumferenceForAge.setMinusOneScore(cursor.getDouble(MINUS_ONE_SCORE_COLUMN_INDEX));
        headCircumferenceForAge.setMinusTwoScore(cursor.getDouble(MINUS_TWO_SCORE_COLUMN_INDEX));
        headCircumferenceForAge.setMinusThreeScore(cursor.getDouble(MINUS_THREE_SCORE_COLUMN_INDEX));
        return headCircumferenceForAge;
    }
}

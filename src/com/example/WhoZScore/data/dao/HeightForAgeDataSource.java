package com.example.WhoZScore.data.dao;

import android.content.Context;
import android.database.Cursor;
import com.example.WhoZScore.data.MySqliteHelper;
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
public class HeightForAgeDataSource extends AbstractZScoreDataSource {


    private MySqliteHelper dbHelper;

    public static final String BOYS_HEIGHT_FOR_AGE = "BoysHeightForAge";
    public static final String GIRLS_HEIGHT_FOR_AGE = "GirlsHeightForAge";




    private String[] scoreColumns = { COLUMN_MINUS_THREE_SCORE,COLUMN_MINUS_TWO_SCORE,COLUMN_MINUS_ONE_SCORE,COLUMN_ZERO_SCORE,COLUMN_ONE_SCORE,COLUMN_TWO_SCORE,COLUMN_THREE_SCORE };

    public HeightForAgeDataSource(Context context) {

        try {
            dbHelper = new MySqliteHelper(context);
            dbHelper.createDataBase();
            dbHelper.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


    private HeightForAge getScoreForBoys(int weeks, int months, int years) {
        HeightForAge heightForAge = null;
        String whereClaue = COLUMN_WEEKS + "=?" + " and " + COLUMN_MONTHS + "=?" + " and " + COLUMN_YEARS + "=?" ;
        String[] whereParameters = new String[]{String.valueOf(weeks),String.valueOf(months),String.valueOf(years)};


        Cursor cursor = dbHelper.myDataBase.query(BOYS_HEIGHT_FOR_AGE,
                scoreColumns, whereClaue, whereParameters, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            heightForAge = cursorToHeightForAge(cursor);
            cursor.moveToNext();

        }


        // make sure to close the cursor
        cursor.close();
        return heightForAge;
    }

    public IZScoreEntity getScore(int weeks, int months, int years, Sex sex){

            if(Sex.FEMALE.equals(sex)){
                return getScoreForGirls(weeks,months,years);
            }else {
                return getScoreForBoys(weeks,months,years);
            }



    }

    private HeightForAge averageHeightForAge(HeightForAge scoreForBoysForMinMonth, HeightForAge scoreForBoysForMaxMonth) {
        HeightForAge heightForAge = new HeightForAge();
        heightForAge.setMinusOneScore(scoreForBoysForMinMonth.getMinusOneScore());
        heightForAge.setMinusTwoScore(scoreForBoysForMinMonth.getMinusTwoScore());
        heightForAge.setMinusThreeScore(scoreForBoysForMinMonth.getMinusThreeScore());
        heightForAge.setOneScore(scoreForBoysForMaxMonth.getOneScore());
        heightForAge.setTwoScore(scoreForBoysForMaxMonth.getTwoScore());
        heightForAge.setThreeScore(scoreForBoysForMaxMonth.getThreeScore());
        double averageZeroScore = (scoreForBoysForMaxMonth.getZeroScore() + scoreForBoysForMinMonth.getZeroScore()) / 2;
        heightForAge.setZeroScore(averageZeroScore);
        return heightForAge;
    }

    private HeightForAge getScoreForGirls(int weeks, int months, int years) {
        HeightForAge heightForAge = null;
        String whereClaue = COLUMN_WEEKS + "=?" + " and " + COLUMN_MONTHS + "=?" + " and " + COLUMN_YEARS + "=?" ;
        String[] whereParameters = new String[]{String.valueOf(weeks),String.valueOf(months),String.valueOf(years)};


        Cursor cursor = dbHelper.myDataBase.query(GIRLS_HEIGHT_FOR_AGE,
                scoreColumns, whereClaue, whereParameters, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            heightForAge = cursorToHeightForAge(cursor);
            cursor.moveToNext();

        }


        // make sure to close the cursor
        cursor.close();
        return heightForAge;
    }

    public List<HeightForAge> getScoreRange(int minWeeks, int maxWeeks, int minMonths, int maxMonths, int minYears, int maxYears, Sex sex){
        List<HeightForAge> heightForAgeList = new ArrayList<HeightForAge>();
        String whereClause = COLUMN_WEEKS + " BETWEEN " + "?" + "AND " + "?" + " AND " + COLUMN_MONTHS + " BETWEEN " + "?" + "AND " + "?" + " AND " + COLUMN_YEARS + " BETWEEN " + "?" + "AND " + "?" ;

        String[] whereParameters = new String[]{String.valueOf(minWeeks),String.valueOf(maxWeeks),String.valueOf(minMonths),String.valueOf(maxMonths),String.valueOf(minYears), String.valueOf(maxYears)};

        String tableName;
        if(Sex.MALE.equals(sex)){
            tableName = BOYS_HEIGHT_FOR_AGE;
        }else {
            tableName = GIRLS_HEIGHT_FOR_AGE;
        }

        Cursor cursor = dbHelper.myDataBase.query(tableName,
                scoreColumns, whereClause, whereParameters, null, null, null);

        cursor.moveToFirst();
        HeightForAge heightForAge = null;
        while (!cursor.isAfterLast()) {
            heightForAge = cursorToHeightForAge(cursor);
            heightForAgeList.add(heightForAge);
            cursor.moveToNext();

        }


        // make sure to close the cursor
        cursor.close();
        return heightForAgeList;

    }

    private HeightForAge cursorToHeightForAge(Cursor cursor) {
        HeightForAge heightForAge = new HeightForAge();
        heightForAge.setThreeScore(cursor.getDouble(cursor.getColumnIndex(COLUMN_THREE_SCORE)));
        heightForAge.setTwoScore(cursor.getDouble(cursor.getColumnIndex(COLUMN_TWO_SCORE)));
        heightForAge.setOneScore(cursor.getDouble(cursor.getColumnIndex(COLUMN_ONE_SCORE)));
        heightForAge.setZeroScore(cursor.getDouble(cursor.getColumnIndex(COLUMN_ZERO_SCORE)));
        heightForAge.setMinusOneScore(cursor.getDouble(cursor.getColumnIndex(COLUMN_MINUS_ONE_SCORE)));
        heightForAge.setMinusTwoScore(cursor.getDouble(cursor.getColumnIndex(COLUMN_MINUS_TWO_SCORE)));
        heightForAge.setMinusThreeScore(cursor.getDouble(cursor.getColumnIndex(COLUMN_MINUS_THREE_SCORE)));
        return heightForAge;
    }
}

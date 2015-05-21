package com.example.WhoZScore.data.dao;

import com.example.WhoZScore.data.entities.IZScoreEntity;
import com.example.WhoZScore.enums.Sex;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/9/15
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractZScoreDataSource implements IZScoreDataSource {

    public final int MINUS_THREE_SCORE_COLUMN_INDEX = 0;
    public final int MINUS_TWO_SCORE_COLUMN_INDEX = 1;
    public final int MINUS_ONE_SCORE_COLUMN_INDEX = 2;
    public final int ZERO_SCORE_COLUMN_INDEX = 3;
    public final int ONE_SCORE_COLUMN_INDEX = 4;
    public final int TWO_SCORE_COLUMN_INDEX = 5;
    public final int THREE_SCORE_COLUMN_INDEX = 6;

    public final String COLUMN_WEEKS = "weeks";
    public final String COLUMN_MONTHS = "months";
    public final String COLUMN_YEARS = "years";
    public final String COLUMN_THREE_SCORE = "threeScore";
    public final String COLUMN_TWO_SCORE = "twoScore";
    public final String COLUMN_ONE_SCORE = "oneScore";
    public final String COLUMN_ZERO_SCORE = "zeroScore";
    public final String COLUMN_MINUS_ONE_SCORE = "minusOneScore";
    public final String COLUMN_MINUS_TWO_SCORE = "minusTwoScore";
    public final String COLUMN_MINUS_THREE_SCORE = "minusThreeScore";
    public final String COLUMN_HEIGHT = "height";

    @Override
    public IZScoreEntity getScore(int weeks, int months, int years, Sex sex) {
        System.out.println("Method not supported by :"+this.getClass());
        return null;
    }

    @Override
    public IZScoreEntity getScore(int height, Sex sex) {
        System.out.println("Method not supported by :"+this.getClass());
        return null;
    }

    @Override
    public List getScoreRange(int minWeeks, int maxWeeks, int minMonths, int maxMonths, int minYears, int maxYears, Sex sex) {
        System.out.println("Method not supported by :"+this.getClass());
        return null;
    }

    @Override
    public List getScoreRange(int minHeight, int maxHeight, Sex sex) {
        System.out.println("Method not supported by :"+this.getClass());
        return null;
    }
}

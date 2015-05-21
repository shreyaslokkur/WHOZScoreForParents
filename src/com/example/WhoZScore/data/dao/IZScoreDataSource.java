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
public interface IZScoreDataSource {

    IZScoreEntity getScore(int weeks, int months, int years, Sex sex);
    IZScoreEntity getScore(int height, Sex sex);
    List getScoreRange(int minWeeks, int maxWeeks, int minMonths, int maxMonths, int minYears, int maxYears, Sex sex);
    List getScoreRange(int minHeight, int maxHeight, Sex sex);
}

package com.example.WhoZScore.data.entities;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/8/15
 * Time: 11:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class WeightForHeight extends AbstractZScoreEntity implements IZScoreEntity {

    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

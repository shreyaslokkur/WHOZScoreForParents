package com.example.WhoZScore.enums;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 8/18/14
 * Time: 1:30 PM
 * To change this template use File | Settings | File Templates.
 */
public enum Sex {

    /**
     * Sex of the patient is male
     */
    MALE("boy"),

    /**
     * Sex of the patient is female
     */
    FEMALE("girl");

    private String gender;

    private Sex(String gender){
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

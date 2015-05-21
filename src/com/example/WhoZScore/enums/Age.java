package com.example.WhoZScore.enums;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 3/31/15
 * Time: 10:42 PM
 * To change this template use File | Settings | File Templates.
 */
public enum Age {
    WEEKS("weeks"),
    MONTHS("months"),
    YEARS("years");

    private String name;

    private Age(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


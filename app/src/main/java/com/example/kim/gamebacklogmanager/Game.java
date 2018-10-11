package com.example.kim.gamebacklogmanager;

import java.util.Date;

public class Game {
    private String mGameTitle;
    private String mGamePlatform;
    private String mGameStatus;
    private String mCurrentDate;

    public Game(String mGameTitle, String mGamePlatform, String mGameStatus, String mCurrentDate) {
        this.mGameTitle = mGameTitle;
        this.mGamePlatform = mGamePlatform;
        this.mGameStatus = mGameStatus;
        this.mCurrentDate = mCurrentDate;
    }

    //getters for all Game data
    public String getmGameTitle() {
        return mGameTitle;
    }
    public String getmGamePlatform() {
        return mGamePlatform;
    }
    public String getmGameStatus() {
        return mGameStatus;
    }
    public String getmCurrentDate() {
        return mCurrentDate;
    }

    //PLACEHOLDERS
    public static final String[] TITLE = {
            "Amsterdam Dam",
            "Amsterdam Weesperplein",
            "Rotterdam Euromast",
            "Den Haag Binnenhof",
            "Utrecht Dom",
            "Groningen Martinitoren",
            "Maastricht Vrijthof",
    };
    public static final String[] PLATFORM = {
            "pc",
            "pc",
            "xbox",
            "ps4",
            "fdsoji",
            "Groningen Martinitoren",
            "Maastricht Vrijthof",
    };
    public static final String[] STATUS = {
            "playi",
            "fdgd",
            "Rotterdam Euromast",
            "Den Haag Binnenhof",
            "Utrecht Dom",
            "Groningen Martinitoren",
            "Maastricht Vrijthof",
    };
    public static final String[] DATE = {
            "141515",
            "Amsterdam Weesperplein",
            "Rotterdam Euromast",
            "Den Haag Binnenhof",
            "Utrecht Dom",
            "Groningen Martinitoren",
            "Maastricht Vrijthof",
    };
}

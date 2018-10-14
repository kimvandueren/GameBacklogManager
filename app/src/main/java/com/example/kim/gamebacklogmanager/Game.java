package com.example.kim.gamebacklogmanager;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Game implements Parcelable {
    private String mGameTitle;
    private String mGamePlatform;
    private String mGameStatus;
    private String mGameNotes;
    private LocalDate mCurrentDate;

    private DateTimeFormatter formatter;
    private String formattedString;

    public Game(String mGameTitle, String mGamePlatform, String mGameStatus, String mGameNotes, LocalDate mCurrentDate) {
        this.mGameTitle = mGameTitle;
        this.mGamePlatform = mGamePlatform;
        this.mGameStatus = mGameStatus;
        this.mGameNotes = mGameNotes;
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
    public String getmGameNotes() {
        return mGameNotes;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getmCurrentDate() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        formattedString = mCurrentDate.format(formatter);
        return formattedString;
    }

    //setters for all Game data
    public void setmGameTitle(String mGameTitle){
        this.mGameTitle = mGameTitle;
    }
    public void setmGamePlatform(String mGamePlatform){
        this.mGamePlatform = mGamePlatform;
    }
    public void setmGameStatus(String mGameStatus){
        this.mGameStatus = mGameStatus;
    }

    public void setmGameNotes(String mGameNotes){
        this.mGameNotes = mGameNotes;
    }

    public void setmCurrentDate(LocalDate mCurrentDate){

        this.mCurrentDate = mCurrentDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mGameTitle);
        dest.writeString(this.mGamePlatform);
        dest.writeString(this.mGameStatus);
        dest.writeString(this.mGameNotes);
        dest.writeLong(this.mCurrentDate.toEpochDay());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected Game(Parcel in) {
        this.mGameTitle = in.readString();
        this.mGamePlatform = in.readString();
        this.mGameStatus = in.readString();
        this.mGameNotes = in.readString();
        this.mCurrentDate = LocalDate.ofEpochDay(in.readLong());
    }

    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}

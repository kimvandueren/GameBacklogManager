package com.example.kim.gamebacklogmanager;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity(tableName = "game")
public class Game implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "gametitle")
    private String mGameTitle;
    @ColumnInfo(name = "gameplatform")
    private String mGamePlatform;
    @ColumnInfo(name = "gamestatus")
    private String mGameStatus;
    @ColumnInfo(name = "gamenotes")
    private String mGameNotes;
    @ColumnInfo(name = "currentdate")
    private String mCurrentDate;

    public Game(String mGameTitle, String mGamePlatform, String mGameStatus, String mGameNotes, String mCurrentDate) {
        this.mGameTitle = mGameTitle;
        this.mGamePlatform = mGamePlatform;
        this.mGameStatus = mGameStatus;
        this.mGameNotes = mGameNotes;
        this.mCurrentDate = mCurrentDate;
    }

    //getters for all Game data
    public long getId(){return id;}
    public String getGameTitle() {
        return mGameTitle;
    }
    public String getGamePlatform() {
        return mGamePlatform;
    }
    public String getGameStatus() {
        return mGameStatus;
    }
    public String getGameNotes() {
        return mGameNotes;
    }
    public String getCurrentDate() {
        return mCurrentDate;
    }

    //setters for all Game data
    public void setId(long id){this.id = id;}
    public void setGameTitle(String mGameTitle){
        this.mGameTitle = mGameTitle;
    }
    public void setGamePlatform(String mGamePlatform){
        this.mGamePlatform = mGamePlatform;
    }
    public void setGameStatus(String mGameStatus){
        this.mGameStatus = mGameStatus;
    }
    public void setGameNotes(String mGameNotes){
        this.mGameNotes = mGameNotes;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setCurrentDate(LocalDate mCurrentDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedString = mCurrentDate.format(formatter);
        this.mCurrentDate = formattedString;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mGameTitle);
        dest.writeString(this.mGamePlatform);
        dest.writeString(this.mGameStatus);
        dest.writeString(this.mGameNotes);
        dest.writeString(this.mCurrentDate);
    }

    protected Game(Parcel in) {
        this.mGameTitle = in.readString();
        this.mGamePlatform = in.readString();
        this.mGameStatus = in.readString();
        this.mGameNotes = in.readString();
        this.mCurrentDate = in.readString();
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

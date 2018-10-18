package com.example.android.quakereport;

import java.text.SimpleDateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by Faiq on 25-Aug-17.
 */

public class Earth {
    private double mag;
    private String loc;
    private long date;
    private String mUrl;


    public Earth(double Mag , String Loc , long Date, String url){

        mag= Mag;
        loc = Loc;
        date = Date;
        mUrl = url;
    }
public double getMag(){
    return mag;
}
    public String getLoc(){
        return loc;
    }
    public long getDate(){
        return date;
    }

    public String getmUrl() {
        return mUrl;
    }
/**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */

}

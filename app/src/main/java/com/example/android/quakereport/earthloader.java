package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by Faiq on 15-Oct-17.
 */

public class earthloader extends AsyncTaskLoader<ArrayList<Earth>> {

    private static final String LOG_TAG = earthloader.class.getName();

    private String mUrl;

    public earthloader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override

    protected void onStartLoading() {

        forceLoad();
    }

    @Override
    public ArrayList<Earth> loadInBackground() {
        if (mUrl == null) {

            return null;
        }


        ArrayList<Earth> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }
}
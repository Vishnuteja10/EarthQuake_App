package com.example.earthquake;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Quake>> {

    private static final String LOG_TAG = EarthquakeLoader.class.getName();
    private String murl;

    public EarthquakeLoader(@NonNull  Context context,String url) {
        super( context );
        murl = url;
    }

    protected void onStartLoading(){
        forceLoad();
    }

    @Nullable

    @Override
    public List<Quake> loadInBackground() {
       if(murl == null){
           return null;
       }
       List<Quake> earthquakes = QueryUtils.fetchEarthquakeData( murl );
       return earthquakes;
    }
}

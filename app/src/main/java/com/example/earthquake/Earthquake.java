package com.example.earthquake;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Earthquake extends AppCompatActivity {

    private static final String USGS_REQ_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10" ;

    private EarthquakeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_earthquake );

       mAdapter = new EarthquakeAdapter( this,new ArrayList<Quake>() );
        ListView listView = findViewById( R.id.list );
        listView.setAdapter( mAdapter );

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Quake currearthquake = mAdapter.getItem( position );
                Uri earthquakeUri = Uri.parse(currearthquake.getUrl());
                Intent websiteIntent =new Intent(Intent.ACTION_VIEW,earthquakeUri);
                startActivity( websiteIntent );

            }
        } );

        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQ_URL);

    }
    private class EarthquakeAsyncTask extends AsyncTask<String,Void, List<Quake> > {

        @Override
        protected List<Quake> doInBackground(String... urls) {
            if(urls.length<1 || urls[0] == null){
                return null;
            }
            List<Quake> result = QueryUtils.extractEarthQuakes( urls[0] );
            return result;

        }
        protected void onPostExecute(List<Quake> data){
            mAdapter.clear();
            if(data != null && !data.isEmpty()){
                mAdapter.addAll( data );
            }
        }
    }
}
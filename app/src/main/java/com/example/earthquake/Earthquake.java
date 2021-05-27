package com.example.earthquake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Earthquake extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_earthquake );

        ArrayList<Quake> EQ = QueryUtils.getEarthQuakes();


        EarthquakeAdapter adapter = new EarthquakeAdapter( this,EQ );
        ListView listView = findViewById( R.id.list );
        listView.setAdapter( adapter );



    }
}
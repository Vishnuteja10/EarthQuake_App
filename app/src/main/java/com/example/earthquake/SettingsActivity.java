package com.example.earthquake;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_settings );
    }
    public static class EarthQuakePreferenceFragment extends PreferenceFragment{

        public void onCreate(Bundle savedInstanceState){
            super.onCreate( savedInstanceState );
            addPreferencesFromResource( R.xml.settings_main);
        }
    }
}
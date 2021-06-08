package com.example.earthquake;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_settings );
    }
    public static class EarthQuakePreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{

        public void onCreate(Bundle savedInstanceState){
            super.onCreate( savedInstanceState );
            addPreferencesFromResource( R.xml.settings_main);

            Preference minMagnitude = findPreference( getString( R.string.settings_min_magnitude_key ) );
            bindPreferenceSummaryToValue(minMagnitude);
        }

        private void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener( this );
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences( preference.getContext() );
            String prferenceString = preferences.getString( preference.getKey(),"" );
            onPreferenceChange( preference,prferenceString );
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringvalue = value.toString();
            preference.setSummary( stringvalue );
            return true;
        }
    }
}
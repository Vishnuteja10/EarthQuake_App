package com.example.earthquake;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
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

            Preference orderBy = findPreference( getString( R.string.settings_min_magnitude_key ) );
            bindPreferenceSummaryToValue( orderBy );
        }


        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringvalue = value.toString();
            if(preference instanceof ListPreference){
                ListPreference listPreference = (ListPreference) preference;
                int prefIndex = listPreference.findIndexOfValue( stringvalue );
                if(prefIndex >= 0){
                    CharSequence[] labels = listPreference.getEntries();
                    preference.setSummary( labels[prefIndex] );
                }
            }else {
                preference.setSummary( stringvalue );
            }
            return true;
        }
        private void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener( this );
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences( preference.getContext() );
            String prferenceString = preferences.getString( preference.getKey(),"" );
            onPreferenceChange( preference,prferenceString );
        }

    }
}
package com.example.earthquake;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.core.content.ContextCompat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Quake> {

    public EarthquakeAdapter(Activity context, ArrayList<Quake> EQ) {
        super( context, 0, EQ );
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitem = convertView;
        if (listitem == null) {
            listitem = LayoutInflater.from( getContext() ).inflate( R.layout.earthquake_listitems, parent, false );
        }
        Quake currentearthquake = getItem( position );
        TextView magTextView = listitem.findViewById( R.id.txt1 );
        double mag = currentearthquake.getMag();
        DecimalFormat formatter = new DecimalFormat( "0.0" );
        String magnitude = formatter.format( mag );
        GradientDrawable magcircle = (GradientDrawable) magTextView.getBackground();
        int magnitudecolor = getMagnitudeColor( mag );
        magcircle.setColor( magnitudecolor );
        magTextView.setText( magnitude );


        TextView offsetTextView = listitem.findViewById( R.id.txt2 );
        TextView locTextView = listitem.findViewById( R.id.txt21 );
        String loc = currentearthquake.getPlace();
        if (loc.contains( "of" )) {
            String[] parts = loc.split( "(?<=of)" );
            String offset = parts[0];
            String location = parts[1];
            offsetTextView.setText( offset );
            locTextView.setText( location );
        } else {
            String offset = "Nearthe";
            String location = loc;
            offsetTextView.setText( offset );
            locTextView.setText( location );
        }

        long time = currentearthquake.getTime();
        Date todate = new Date( time );
        SimpleDateFormat dateFormat = new SimpleDateFormat( "MMM dd, yyyy" );
        String date = dateFormat.format( todate );
        TextView timeTextView = listitem.findViewById( R.id.txt3 );
        timeTextView.setText( date );

        return listitem;
    }

    private int getMagnitudeColor(double mag) {
        int magnitudeColorResourceid;
        int magnitudeFloor = (int) Math.floor( mag );
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceid = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceid = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceid = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceid = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceid = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceid = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceid = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceid = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceid = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceid = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor( getContext(), magnitudeColorResourceid );
    }
}
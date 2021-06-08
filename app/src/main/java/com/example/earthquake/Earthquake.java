package com.example.earthquake;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager.LoaderCallbacks;

import java.util.ArrayList;
import java.util.List;

public class Earthquake extends AppCompatActivity implements LoaderCallbacks<List<Quake>> {

    private static final String USGS_REQ_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query" ;

    private EarthquakeAdapter mAdapter;

    private static final int earthquake_loader_id = 1;
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_earthquake );

        mAdapter = new EarthquakeAdapter( this, new ArrayList<Quake>() );
        ListView listView = findViewById( R.id.list );
        mEmptyStateTextView = (TextView) findViewById( R.id.empty_view );
        listView.setEmptyView( mEmptyStateTextView );
        listView.setAdapter( mAdapter );


        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Quake currearthquake = mAdapter.getItem( position );
                Uri earthquakeUri = Uri.parse( currearthquake.getUrl() );
                Intent websiteIntent = new Intent( Intent.ACTION_VIEW, earthquakeUri );
                startActivity( websiteIntent );

            }
        } );

      /*  EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQ_URL); */
        // LoaderManager loaderManager = getLoaderManager();
        //loaderManager.initLoader( earthquake_loader_id,null,this );

        ConnectivityManager conmag = (ConnectivityManager)
                getSystemService( Context.CONNECTIVITY_SERVICE );
        NetworkInfo networkInfo = conmag.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            androidx.loader.app.LoaderManager loaderManager1 = null;
            loaderManager1.getInstance( this ).initLoader( earthquake_loader_id, null, this );
        } else {
            View loadingindicator = findViewById( R.id.loading_indicator );
            loadingindicator.setVisibility( View.GONE );

            mEmptyStateTextView.setText( R.string.no_internet );
        }
    }

    @NonNull

    @Override
    public androidx.loader.content.Loader<List<Quake>> onCreateLoader(int id, @Nullable  Bundle args) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences( this );
        String minMagnitude = sharedPreferences.getString( getString( R.string.settings_min_magnitude_key ),getString( R.string.settings_min_magnitude_default ) );
        Uri baseuri = Uri.parse( USGS_REQ_URL );
        Uri.Builder uribuilder = baseuri.buildUpon();
        uribuilder.appendQueryParameter( "format","geojson" );
        uribuilder.appendQueryParameter( "limit","10" );
        uribuilder.appendQueryParameter( "minmag",minMagnitude );
        uribuilder.appendQueryParameter( "orderby","time" );
        return new EarthquakeLoader( this,uribuilder.toString() );
    }

    @Override
    public void onLoadFinished( androidx.loader.content.Loader<List<Quake>> loader, List<Quake> data) {

        View loadingIndicator = findViewById( R.id.loading_indicator );
        loadingIndicator.setVisibility( View.GONE );

        mEmptyStateTextView.setText(R.string.no_earthquakes);

        mAdapter.clear();

        if(data != null && !data.isEmpty()){
            mAdapter.addAll( data );
        }

    }

    @Override
    public void onLoaderReset( androidx.loader.content.Loader<List<Quake>> loader) {

        mAdapter.clear();
    }



    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate( R.menu.menu,menu );
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_settings){
            Intent settingsIntent = new Intent(this,SettingsActivity.class);
            startActivity( settingsIntent );
            return true;
        }
        return super.onOptionsItemSelected( item );
    }

   /* private class EarthquakeAsyncTask extends AsyncTask<String,Void, List<Quake> > {

        @Override
        protected List<Quake> doInBackground(String... urls) {
            if(urls.length<1 || urls[0] == null){
                return null;
            }
            List<Quake> result = QueryUtils.fetchEarthquakeData( urls[0] );
            return result;

        }
        protected void onPostExecute(List<Quake> data){
            mAdapter.clear();
            if(data != null && !data.isEmpty()){
                mAdapter.addAll( data );
            }

       }
       }

    */
}

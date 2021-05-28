package com.example.earthquake;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

        /** Sample JSON response for a USGS query */

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();


    private QueryUtils() {
      }


      public static List<Quake> fetchEarthquakeData(String requestUrl){

        URL url = createUrl( requestUrl );
        String jsonResponse = null;
        try{
            jsonResponse = makeHttpRequest( url );
        }catch (IOException e){
            Log.e(LOG_TAG,"problem making HTTP request",e);
        }
        List<Quake> earthquakes = extractEarthQuakes( jsonResponse );

        return earthquakes;
      }
      private static URL createUrl(String stringUrl){
          URL url = null;
          try{
              url = new URL( stringUrl );
          }catch (MalformedURLException e){
              Log.e(LOG_TAG,"problem building the URL",e);
          }
          return url;
      }

      private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse ="";
        if(url == null){
            return jsonResponse;
        }
          HttpURLConnection urlConnection =null;
          InputStream inputStream =null;

          try{
              urlConnection = (HttpURLConnection) url.openConnection();
              urlConnection.setReadTimeout( 10000 );
              urlConnection.setConnectTimeout( 15000 );
              urlConnection.setRequestMethod( "GET" );
              urlConnection.connect();

              if(urlConnection.getResponseCode()==200){
                  inputStream = urlConnection.getInputStream();
                  jsonResponse = readFromStream(inputStream);
              }
              else{
                  Log.e(LOG_TAG,"error response code: "+ urlConnection.getResponseCode());
              }
          }catch (IOException e){
              Log.e(LOG_TAG,"problem retrieving json result",e);
          }
          finally {
              if (urlConnection != null){
                  urlConnection.disconnect();
              }
              if(inputStream != null){
                  inputStream.close();
              }
          }
          return jsonResponse;
      }

      private static String readFromStream(InputStream inputStream) throws IOException{

        StringBuilder output = new StringBuilder();
        if(inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader( inputStream, Charset.forName( "UTF-8" ) );
            BufferedReader reader = new BufferedReader( inputStreamReader );
            String line = reader.readLine();
            while (line!=null){
                output.append( line );
                line = reader.readLine();
            }
        }
        return output.toString();
      }

      public static List<Quake> extractEarthQuakes(String earthquakeJSON){

        if(TextUtils.isEmpty(earthquakeJSON )){
            return null;
        }

          List<Quake> Earthquakes = new ArrayList<>();

          try{
              //JSONObject root = new JSONObject(SAMPLE_JSON_RESPONSE);
              JSONObject root = new JSONObject(earthquakeJSON);
              JSONArray earthquakes = root.getJSONArray("features");
              for(int i = 0; i<earthquakes.length();i++) {

                 JSONObject earthquake = earthquakes.getJSONObject( i );
                 JSONObject properties = earthquake.getJSONObject( "properties" );
                 double mag = properties.getDouble( "mag" );
                 String place = properties.getString( "place" );
                 long time = properties.getLong( "time" );
                 String url = properties.getString( "url" );

                 Quake currearthquake = new Quake( mag,place,time,url );
                 Earthquakes.add(currearthquake);
              }


          } catch (JSONException e) {
              Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
          }

          return Earthquakes;
      }

    }


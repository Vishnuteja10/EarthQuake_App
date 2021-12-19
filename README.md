
# EarthQuake_App

This app displays a list of earthquake data from all over world from USGS(U.S. Geological Survey organisation) web-server .with help of API request, got earthquake data from USGS server in Json format.  

More Info on USGS Earthquake API :https://earthquake.usgs.gov/fdsnws/event/1/

 .Used AsyncTaskLoader in background thread where HTTP Network request is done to fetch data from server.
 .With the help of ListView and ArrayAdapter, fetched data is displayed .  
 .A ProgressBar is used to show the Network activity . If there is problem with loading data then an alternate TextView is shown which tells the state  
 .Used Preference Fragments for Filters                
    .OrderBy : We can arrange data by decresing order of earthquake magnitude or by most recently occured
    .We can also set minimum magnitude
    
![order by mag](https://user-images.githubusercontent.com/66770891/146666503-9a1c2656-7d39-4a7a-9856-47bfc710060e.jpeg)

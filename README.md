
# EarthQuake_App

This app displays a list of earthquake data from all over world from USGS(U.S. Geological Survey organisation) web-server .with help of API request, got earthquake data from USGS server in Json format.  

More Info on USGS Earthquake API :https://earthquake.usgs.gov/fdsnws/event/1/

 .Used AsyncTaskLoader in background thread where HTTP Network request is done to fetch data from server.
 .With the help of ListView and ArrayAdapter, fetched data is displayed .  
 .A ProgressBar is used to show the Network activity . If there is problem with loading data then an alternate TextView is shown which tells the state  
 .Used Preference Fragments for Filters                
    .OrderBy : We can arrange data by decresing order of earthquake magnitude or by most recently occured
    .We can also set minimum magnitude
    
![Most recently occur](https://user-images.githubusercontent.com/66770891/146666591-60f3065f-1f8b-4b76-b02b-80a19b0feb02.jpeg)   
    
![settings](https://user-images.githubusercontent.com/66770891/146666621-ab9cf20b-c62e-41d5-a3f6-f21415c65beb.jpeg)

![orderby](https://user-images.githubusercontent.com/66770891/146666626-72ddf76c-8b3b-4bf6-9219-678596f93d57.jpeg)

![order by mag](https://user-images.githubusercontent.com/66770891/146666503-9a1c2656-7d39-4a7a-9856-47bfc710060e.jpeg)


# WorkFlow


https://user-images.githubusercontent.com/66770891/146666966-80cd77cf-da92-41ce-b332-95bf30ef887f.mp4



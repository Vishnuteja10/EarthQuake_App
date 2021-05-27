package com.example.earthquake;

public class Quake {
    private  double mag;
    private  String place ;
    private  long time;
    private String url;



    public Quake(double mag, String place, long time, String url) {
        this.mag = mag;
        this.place = place;
        this.time = time;
        this.url = url;
    }

    public Double getMag(){
        return mag;
    }
    public String getPlace(){
        return place;
    }
    public long getTime(){
        return time;
    }
    public String getUrl(){
        return url;
    }
}

package com.example.android.quakereport;

/**
 * Created by paul on 3/31/18.
 */

public class Earthquake {
    // Magnitude of the earthquake e.g. 1.2, 4.5)
    private double mMagnitude;

    // City of origin (e.g. San Fran, London)
    private String mCity;

    //Time of the earthquake
    private Long mTimeInMilliseconds;

    //Website url of the earthquake
    private String mURL;

/*
* @param Mag  is the magnitude of the earthquake
* @param City is the city where the earthquake was
* @param mTimeInMilliseconds is the unix long form time returned
* */

    //constructor for a basic earthquake object
    public Earthquake(double Mag, String City, long TimeInMilliseconds,String url)
    {
        mMagnitude = Mag;
        mCity = City;
        mTimeInMilliseconds = TimeInMilliseconds;
        mURL = url;
    }

     //Get the magnitude of the quake
    public Double getMagnitude() {
        return mMagnitude;
    }

     //Get the City of where the earthquake took place
    public String getCity() {
        return mCity;
    }

    //Get the unix time of earthquake
    public Long getTimeInMilliseconds() { return mTimeInMilliseconds;
    }

    //Get the City of where the earthquake took place
    public String getURL() {
        return mURL;
    }

}
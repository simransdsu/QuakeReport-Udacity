package com.example.android.quakereport.Models;

import java.util.GregorianCalendar;

public class EarthquakeInfo
{
    private double magnitude;
    private String city;
    private GregorianCalendar date;

    public EarthquakeInfo(double magnitude, String city, GregorianCalendar date)
    {
        this.magnitude = magnitude;
        this.city = city;
        this.date = date;
    }

    public double getMagnitude()
    {
        return magnitude;
    }

    public void setMagnitude(double magnitude)
    {
        this.magnitude = magnitude;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public GregorianCalendar getDate()
    {
        return date;
    }

    public void setDate(GregorianCalendar date)
    {
        this.date = date;
    }
}

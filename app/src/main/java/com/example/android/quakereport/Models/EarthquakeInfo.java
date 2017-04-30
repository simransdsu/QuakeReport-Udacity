package com.example.android.quakereport.Models;

import java.text.DecimalFormat;
import java.util.Calendar;

public class EarthquakeInfo
{
    private double magnitude;
    private String city;
    private Calendar date;

    public EarthquakeInfo(double magnitude, String city, Calendar date)
    {
        setMagnitude(magnitude);
        setCity(city);
        setDate(date);
    }

    public double getMagnitude()
    {
        return magnitude;
    }

    public void setMagnitude(double magnitude)
    {
        DecimalFormat formatter = new DecimalFormat("0.0");
        this.magnitude = Double.parseDouble(formatter.format(magnitude));
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public Calendar getDate()
    {
        return date;
    }

    public void setDate(Calendar date)
    {
        this.date = date;
    }
}

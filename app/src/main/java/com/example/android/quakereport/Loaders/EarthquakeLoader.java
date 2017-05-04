package com.example.android.quakereport.Loaders;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import com.example.android.quakereport.Models.EarthquakeInfo;
import com.example.android.quakereport.Utils.QueryUtils;

import java.util.List;


public class EarthquakeLoader extends AsyncTaskLoader<List<EarthquakeInfo>>
{
    private String url;

    public EarthquakeLoader(Context context, String url)
    {
        super(context);

        this.url = url;
    }

    @Override
    protected void onStartLoading()
    {
        forceLoad();
    }

    @Override
    public List<EarthquakeInfo> loadInBackground()
    {
        if(url == null)
            return null;

        return QueryUtils.fetchEarthquakeData(url);
    }
}

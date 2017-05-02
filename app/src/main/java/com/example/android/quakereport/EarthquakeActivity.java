/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.quakereport.Adapters.earthquake.EarthQuakeListAdapter;
import com.example.android.quakereport.Models.EarthquakeInfo;
import com.example.android.quakereport.Utils.QueryUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements OnItemClickListener{

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private List<EarthquakeInfo> earthquakes;
    private final String USGS_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5";
    private  EarthQuakeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        earthquakes = new ArrayList<>();

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        adapter = new EarthQuakeListAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        // Set the onclick event on the list item
        earthquakeListView.setOnItemClickListener(this);

        new EarthQuakeInformationAsyncTask().execute(USGS_URL);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        EarthquakeInfo earthquakeInfo = earthquakes.get(position);
        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(earthquakeInfo.getWebsite()));
        startActivity(websiteIntent);
    }


    private class EarthQuakeInformationAsyncTask extends AsyncTask<String, Void, List<EarthquakeInfo>>
    {

        @Override
        protected List<EarthquakeInfo> doInBackground(String... params)
        {
            if(params.length != 1)
            {
                return null;
            }

            return QueryUtils.fetchEarthquakeData(params[0]);
        }

        @Override
        protected void onPostExecute(List<EarthquakeInfo> earthquakeInfoList)
        {
            earthquakes = earthquakeInfoList;

            adapter.clear();
            adapter.addAll(earthquakes);
            adapter.notifyDataSetChanged();
        }
    }
}

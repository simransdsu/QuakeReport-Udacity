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

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.quakereport.Adapters.earthquake.EarthQuakeListAdapter;
import com.example.android.quakereport.Loaders.EarthquakeLoader;
import com.example.android.quakereport.Models.EarthquakeInfo;
import com.example.android.quakereport.Utils.ProgressSpinner;
import com.example.android.quakereport.Utils.QueryUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity
        implements OnItemClickListener,
                   LoaderManager.LoaderCallbacks<List<EarthquakeInfo>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private TextView emptyTextView;
    private ProgressSpinner progressSpinner;

    private List<EarthquakeInfo> earthquakes;
    private final String USGS_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=3&limit=50";
    private  EarthQuakeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        earthquakes = new ArrayList<>();

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        emptyTextView = (TextView) findViewById(R.id.emptyTextView);
        earthquakeListView.setEmptyView(emptyTextView);
        progressSpinner = new ProgressSpinner(this);

        // Create a new {@link ArrayAdapter} of earthquakes
        adapter = new EarthQuakeListAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        // Set the onclick event on the list item
        earthquakeListView.setOnItemClickListener(this);

        if(isNetworkAvailable()) {
            getSupportLoaderManager().initLoader(1, null, this);
        } else {
            progressSpinner.dismissProgressDialog();
            emptyTextView.setText("No Internet Available");
        }



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        EarthquakeInfo earthquakeInfo = earthquakes.get(position);
        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(earthquakeInfo.getWebsite()));
        startActivity(websiteIntent);
    }

    @Override
    public Loader<List<EarthquakeInfo>> onCreateLoader(int id, Bundle args)
    {
        progressSpinner.showProgressDialog();
        return new EarthquakeLoader(this, USGS_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<EarthquakeInfo>> loader, List<EarthquakeInfo> data)
    {
        earthquakes = data;

        adapter.clear();
        if(earthquakes != null && !earthquakes.isEmpty())
        {
            adapter.addAll(earthquakes);
            adapter.notifyDataSetChanged();
        }

        progressSpinner.dismissProgressDialog();
        emptyTextView.setText("No Earthquakes found");
    }

    @Override
    public void onLoaderReset(Loader<List<EarthquakeInfo>> loader)
    {
        adapter.clear();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        return (networkInfo != null && networkInfo.isConnected());
    }

}

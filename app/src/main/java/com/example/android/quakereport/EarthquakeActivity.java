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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.quakereport.Adapters.earthquake.EarthQuakeListAdapter;
import com.example.android.quakereport.Models.EarthquakeInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<EarthquakeInfo> earthquakes = new ArrayList<>();
        earthquakes.add(new EarthquakeInfo(7.2, "San Francisco", new GregorianCalendar(2016, 2, 2)));
        earthquakes.add(new EarthquakeInfo(6.1, "London", new GregorianCalendar(2015, 6, 21)));
        earthquakes.add(new EarthquakeInfo(3.9, "Tokyo", new GregorianCalendar(2014, 7, 15)));
        earthquakes.add(new EarthquakeInfo(5.4, "Mexico City", new GregorianCalendar(2014, 2, 27)));
        earthquakes.add(new EarthquakeInfo(2.8, "Moscow", new GregorianCalendar(2016, 12, 7)));
        earthquakes.add(new EarthquakeInfo(4.9, "Rio de Jeneiro", new GregorianCalendar(2016, 9, 3)));
        earthquakes.add(new EarthquakeInfo(1.6, "Paris", new GregorianCalendar(2016, 6, 9)));

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthQuakeListAdapter adapter = new EarthQuakeListAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }
}

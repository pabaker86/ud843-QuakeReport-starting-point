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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.URI;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquakes.
        final ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();
        // Find a reference to the {@link ListView} in the layout
        final EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this, earthquakes);

        // Set the adapter on he {@link ListView}
        // so the list can be populated in the user interface
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(earthquakeAdapter);
        //TO DO Set an onclick listener for our list
        // Set a click listener on the numbers view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    //create an Earthquake object to track the position of each word item clicked
                    Earthquake earthquakesPosition = earthquakes.get(position);

                //The code in this method will be executed when the earthquake Data Item is clicked on.
                //create a string which will serve as the USGA value to search off of .
                //TO DO we need take each objects item's data and pass that through to a usable URL
                // URL https://earthquake.usgs.gov/fdsnws/event/1/[METHOD[?PARAMETERS]]
                //methods
                Uri earthquakeUri = Uri.parse(earthquakesPosition.getURL());
                    Intent InternetIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                    startActivity(InternetIntent);

            }

        });
    }
}

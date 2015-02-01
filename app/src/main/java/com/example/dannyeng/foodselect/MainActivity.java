package com.example.dannyeng.foodselect;

import com.example.dannyeng.foodselect.YelpAPI;
import com.example.dannyeng.foodselect.MyLocation;
import android.content.Intent;
import android.util.Log;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.os.AsyncTask;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.location.LocationProvider;


import com.beust.jcommander.JCommander;


public class MainActivity extends ActionBarActivity {

    private static final String API_HOST = "api.yelp.com";
    private static final String DEFAULT_TERM = "dinner";
    private static final String DEFAULT_LOCATION = "San Francisco, CA";
    private static final int SEARCH_LIMIT = 3;
    private static final String SEARCH_PATH = "/v2/search";
    private static final String BUSINESS_PATH = "/v2/business";

    /*
     * Update OAuth credentials below from the Yelp Developers API site:
     * http://www.yelp.com/developers/getting_started/api_access
     */
    private static final String CONSUMER_KEY = "kmUv_m_f7FKQnhtp871VCw";
    private static final String CONSUMER_SECRET = "2ubrjqEWEz5rfc1gkfS-4Ji37-g";
    private static final String TOKEN = "PqN9k_L6aV0QRTqgS0olT6NzH44_eCRo";
    private static final String TOKEN_SECRET = "b4WZlv30A5XzBafBhO7uecsHCKg";
    private static String[] restaurants = new String[12];

    private class ConnectToApi extends AsyncTask<String, Void, Boolean> {
        protected Boolean doInBackground(String... params) {
            YelpAPI yelpApi = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
            restaurants = YelpAPI.queryAPI(yelpApi, params[0], params[1], params[2]);
            //initiate main activity 2 with these restaurant values
            Intent i = new Intent(getApplicationContext(),MainActivity3.class);
            i.putExtra("name1", restaurants[0]);
            i.putExtra("add1", restaurants[1]);
            i.putExtra("phone1", restaurants[2]);
            i.putExtra("name2", restaurants[3]);
            i.putExtra("add2", restaurants[4]);
            i.putExtra("phone2", restaurants[5]);
            i.putExtra("name3", restaurants[6]);
            i.putExtra("add3", restaurants[7]);
            i.putExtra("phone3", restaurants[8]);
            i.putExtra("review1", restaurants[9]);
            i.putExtra("review2", restaurants[10]);
            i.putExtra("review3", restaurants[11]);
            startActivity(i);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Dispays the category name
        final TextView textOne = (TextView)findViewById(R.id.textView1);

        // the random button use to pick category of food
        // image is grubit_title2
        ImageButton pushMe1 =(ImageButton)findViewById(R.id.imageButton1);


        //category of food (need to finish)
        final String[] myNames = {"Chinese","American","Shawarma","Asian Fusion","Fast Food","Pizza","Buffet","Sushi","Burgers","Chicken Wings","Diners","Greek","Indian","Italian","Japanese","Korean","Persian","Seafood","Steakhouses","Thai","Vegetarian","Vietnamese","Hotpot","Soul Food"};

        //main button
        pushMe1.setOnClickListener(new View.OnClickListener() {
            LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();


            @Override
            public void onClick(View v) {
                int rando1 = (int)(Math.random()*myNames.length);
                textOne.setText(myNames[rando1]);



                ImageButton pushYelp = (ImageButton)findViewById(R.id.imageButton3);

                pushYelp.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view){
                        new ConnectToApi().execute(textOne.getText().toString(), String.valueOf(latitude), String.valueOf(longitude));


                    }
                });


            }
        });

        //title button that brings you to the info page
        ImageButton pushMe2 = (ImageButton)findViewById(R.id.imageButton);

        pushMe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(i2);
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
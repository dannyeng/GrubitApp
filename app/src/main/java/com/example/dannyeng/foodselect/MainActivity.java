package com.example.dannyeng.foodselect;

import com.example.dannyeng.foodselect.YelpAPI;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.os.AsyncTask;

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

    private class ConnectToApi extends AsyncTask<Void, Void, Boolean> {
        protected Boolean doInBackground(Void... params) {
            YelpAPI yelpApi = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
            YelpAPI.queryAPI(yelpApi);
            return true;

        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(Long result) {
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
        final String[] myNames = {"Chinese","Mexican","Shawarma","Fancy White Food","Macdonalds","Pizza","Buffet!","All You Can Eat Sushi"};

        //main button
        pushMe1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                new ConnectToApi().execute();
                int rando1 = (int)(Math.random()*8);
                textOne.setText(myNames[rando1]);

            }
        });

        //title button that brings you to the info page
       ImageButton pushMe2 = (ImageButton)findViewById(R.id.imageButton);

        pushMe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),MainActivity2.class));
            }
        });

        ImageButton pushYelp = (ImageButton)findViewById(R.id.imageButton3);

        pushYelp.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),MainActivity3.class));
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



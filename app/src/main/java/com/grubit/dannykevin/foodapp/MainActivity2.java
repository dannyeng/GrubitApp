package com.grubit.dannykevin.foodapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import java.io.IOException;
import java.util.List;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

public class MainActivity2 extends ActionBarActivity implements OnMapReadyCallback{
    MapFragment mapFragment;
    String address, name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        Intent intent = getIntent();
        address = intent.getStringExtra("address");
        name = intent.getStringExtra("name");

        // the info page button which lets you go back to the main page.
        // button is the title (REMINDER)
        //**no idea what i want this page to do, maybe some information page

        ImageButton pushMe3 = (ImageButton)findViewById(R.id.imageButton2);

        pushMe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        map.setMyLocationEnabled(true);



        if(address != null && !address.equals("")){
            Geocoder geocoder = new Geocoder(getApplicationContext());
            try {
                // Getting a maximum of 3 Address that matches the input text
                List<Address> addresses = geocoder.getFromLocationName(address, 3);
                double latitudeDest = addresses.get(0).getLatitude();
                double longitudeDest = addresses.get(0).getLongitude();
                LatLng latlngDest = new LatLng(latitudeDest, longitudeDest);
                map.addMarker(new MarkerOptions()
                        .position(latlngDest)
                        .title(name));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlngDest, 12.7f));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //AsyncTask that takes an address and creates a map marker
    private class GeocoderTask extends AsyncTask<String, Void, List<Address>>{

        @Override
        protected List<Address> doInBackground(String... locationName) {
            // Creating an instance of Geocoder class
            Geocoder geocoder = new Geocoder(getApplicationContext());
            List<Address> addresses = null;

            try {
                // Getting a maximum of 3 Address that matches the input text
                addresses = geocoder.getFromLocationName(locationName[0], 3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addresses;
        }

        @Override
        protected void onPostExecute(List<Address> addresses) {

            // Adding Markers on Google Map for each matching address
            for(int i=0;i<addresses.size();i++){

                Address address = (Address) addresses.get(i);

                // Creating an instance of GeoPoint, to display in Google Map
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                String addressText = String.format("%s, %s",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                        address.getCountryName());

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(addressText);

                mapFragment.getMap().addMarker(markerOptions);

            }
        }
    }

}



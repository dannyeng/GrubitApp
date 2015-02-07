package com.grubit.dannykevin.foodapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.util.Log;
import java.net.URL;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.io.IOException;

import android.os.AsyncTask;
import android.net.Uri;



public class MainActivity3 extends ActionBarActivity {
    public String[] restaurants = new String[12];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3);

        Intent intent = getIntent();
        restaurants[0] = intent.getStringExtra("name1");
        restaurants[1] = intent.getStringExtra("add1");
        restaurants[2] = intent.getStringExtra("phone1");
        restaurants[3] = intent.getStringExtra("name2");
        restaurants[4] = intent.getStringExtra("add2");
        restaurants[5] = intent.getStringExtra("phone2");
        restaurants[6] = intent.getStringExtra("name3");
        restaurants[7] = intent.getStringExtra("add3");
        restaurants[8] = intent.getStringExtra("phone3");
        restaurants[9] = intent.getStringExtra("review1");
        restaurants[10] = intent.getStringExtra("review2");
        restaurants[11] = intent.getStringExtra("review3");


        ImageButton backhome = (ImageButton)findViewById(R.id.imageButton4);

        final TextView name1 = (TextView)findViewById(R.id.textView);
        final TextView add1 = (TextView)findViewById(R.id.textView2);
        final TextView name2 = (TextView)findViewById(R.id.textView3);
        final TextView add2 = (TextView)findViewById(R.id.textView4);
        final TextView name3 = (TextView)findViewById(R.id.textView5);
        final TextView add3 = (TextView)findViewById(R.id.textView6);

        /*final TextView phonee1 = (TextView)findViewById(R.id.phonee1);
        phonee1.setText(restaurants[2]);
        final TextView phonee2 = (TextView)findViewById(R.id.phonee2);
        phonee2.setText(restaurants[5]);
        final TextView phonee3 = (TextView)findViewById(R.id.phonee3);
        phonee3.setText(restaurants[8]);*/

        name1.setText(restaurants[0]);
        add1.setText(restaurants[1]);
        name2.setText(restaurants[3]);
        add2.setText(restaurants[4]);
        name3.setText(restaurants[6]);
        add3.setText(restaurants[7]);

        ImageView rev1 = (ImageView) findViewById(R.id.review1);
        String url1 = restaurants[9];
        new ImageLoadTask(url1, rev1).execute();


        ImageView rev2 = (ImageView) findViewById(R.id.review2);
        String url2 = restaurants[10];
        new ImageLoadTask(url2, rev2).execute();

        ImageView rev3 = (ImageView) findViewById(R.id.review3);
        String url3 = restaurants[11];
        new ImageLoadTask(url3, rev3).execute();





        final Button phone1 = (Button)findViewById(R.id.phone1);
        phone1.setText(restaurants[2]);
        phone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone1.getText()));
                startActivity(callIntent);

            }
        });

        final Button phone2 = (Button)findViewById(R.id.phone2);
        phone2.setText(restaurants[5]);
        phone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone2.getText()));
                startActivity(callIntent);

            }
        });

        final Button phone3 = (Button)findViewById(R.id.phone3);
        phone3.setText(restaurants[8]);
        phone3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone3.getText()));
                startActivity(callIntent);

            }
        });


        final Button direct1 = (Button)findViewById(R.id.direct1);

       direct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mapInt1 = new Intent(getApplicationContext(),MainActivity2.class);
                mapInt1.putExtra("address", restaurants[1]);
                mapInt1.putExtra("name", restaurants[0]);
                startActivity(mapInt1);

            }
        });


        final Button direct2 = (Button)findViewById(R.id.direct2);

        direct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mapInt2 = new Intent(getApplicationContext(),MainActivity2.class);
                mapInt2.putExtra("address", restaurants[4]);
                mapInt2.putExtra("name", restaurants[3]);
                startActivity(mapInt2);

            }
        });

        final Button direct3 = (Button)findViewById(R.id.direct3);

        direct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mapInt3 = new Intent(getApplicationContext(),MainActivity2.class);
                mapInt3.putExtra("address", restaurants[7]);
                mapInt3.putExtra("name", restaurants[6]);
                startActivity(mapInt3);

            }
        });





        final ImageButton yelpbutton = (ImageButton)findViewById(R.id.yelpbutton);

        yelpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.yelp.com");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });



        backhome.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity3, menu);
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

    public void onClick1(View v){
        Intent mapInt1 = new Intent(getApplicationContext(),MainActivity2.class);
        mapInt1.putExtra("address", restaurants[1]);
        mapInt1.putExtra("name", restaurants[0]);
        startActivity(mapInt1);
    }

    public void onClick2(View v){
        Intent mapInt2 = new Intent(getApplicationContext(),MainActivity2.class);
        mapInt2.putExtra("address", restaurants[4]);
        mapInt2.putExtra("name", restaurants[3]);
        startActivity(mapInt2);
    }

    public void onClick3(View v){
        Intent mapInt3 = new Intent(getApplicationContext(),MainActivity2.class);
        mapInt3.putExtra("address", restaurants[7]);
        mapInt3.putExtra("name", restaurants[6]);
        startActivity(mapInt3);
    }

    private class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(this.url).getContent());
                Log.i("Completion", "Finished Downloading Image");
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }

}


package com.example.dannyeng.foodselect;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final TextView textOne = (TextView)findViewById(R.id.textView1);

        //Button pushMe = (Button)findViewById(R.id.button1);

        ImageButton pushMe1 =(ImageButton)findViewById(R.id.imageButton1);

        final String[] myNames = {"Chinese","Mexican","Shawarma","Fancy White Food","Macdonalds","Pizza","Buffet!","All You Can Eat Sushi"};


        pushMe1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                int rando1 = (int)(Math.random()*8);
                textOne.setText(myNames[rando1]);

            }
        });


       ImageButton pushMe2 = (ImageButton)findViewById(R.id.imageButton);

        pushMe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),MainActivity2.class));
            }
        });

     //   ImageButton pushMe3 = (ImageButton)findViewById(R.id.imageButton2);

     //  pushMe3.setOnClickListener(new View.OnClickListener() {
        //    @Override
         //   public void onClick(View v) {

           //    startActivity(new Intent(getApplicationContext(),MainActivity.class ));
          // }
      //  });

        //pushMe.setOnClickListener(new View.OnClickListener() {


         //@Override
          // public void onClick(View v) {
          //   int rando = (int)(Math.random()*8);
            // textOne.setText(myNames[rando]);

             //   }
          // });



    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}

package com.example.dk.bfit1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity  {

    int n=0;
    int k=0;
    TextView tw;
    Button[] buttons = new Button[48];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tw=(TextView)findViewById(R.id.twview);


        for(int j=0; j<48; j++) {
            String buttonID = "a"+j;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[j] = ((Button) findViewById(resID));
        }

    }

    void check(View view){

        Log.d("dka","the button clicked "+((Button)view).getId());
         Log.d("dka","the value of n "+n);
        if(buttons[n].getId()==((Button)view).getId()){
            k++;
            Log.d("dka","the value of k"+k);
        }
        else
            view.setBackgroundColor(Color.RED);
        tw.setText("0"+k);


    }

    private class Runthe implements Runnable{

        @Override
        public void run() {
            int i = 0;
            for(i=0;i<80;i++){






                n = (int) (Math.random() * 47 + 1);
                final int finalN = n;

                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    buttons[finalN].setBackgroundColor(Color.GREEN);
                                    Log.d("dka","main"+finalN);
                                    Log.d("dka","main id"+buttons[finalN].getId());
                                }
                            });
                        }
                    });

                    Thread.sleep(600);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            buttons[finalN].setBackgroundColor(Color.WHITE);
                            Log.d("dka","main after"+finalN);
                        }
                    });



                    Log.d("dk", "BUtton icon is" + n);

                } catch (InterruptedException ex) {
                    Log.d("dk", "error at " + ex);
                }
            }


        }

    }

            public void runme(View vie){
                Thread myThread=new Thread(new Runthe());
                myThread.start();

            }





    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(this, About.class));
                return true;
            case R.id.help:
                startActivity(new Intent(this, Help.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}

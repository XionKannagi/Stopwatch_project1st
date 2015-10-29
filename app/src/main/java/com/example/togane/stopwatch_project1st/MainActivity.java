package com.example.togane.stopwatch_project1st;

import android.app.Activity;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;


public class MainActivity extends ActionBarActivity {

    Chronometer mChronometer;
    Button start,stop,reset;
    private long stopTime;
    private long startTime = 0;
    private boolean isFirst = false;
    private boolean isStart = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChronometer = (Chronometer)findViewById(R.id.chronometer);
        start = (Button)findViewById(R.id.start);
        reset = (Button)findViewById(R.id.reset);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isStart) {
                    if (!isFirst) {
                        mChronometer.setBase(SystemClock.elapsedRealtime());
                        startTime = SystemClock.elapsedRealtime();
                        mChronometer.start();
                        isFirst = true;
                    } else {
                        long time1 = stopTime - startTime;
                        long time2 = SystemClock.elapsedRealtime() - time1;
                        mChronometer.setBase(time2);
                        mChronometer.start();
                        startTime=time2;
                    }
                    isStart = true;
                    start.setText("ストップ");
                } else if (isStart) {
                    stopTime = SystemClock.elapsedRealtime();
                    mChronometer.stop();
                    isStart = false;
                    start.setText("開始");
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                mChronometer.stop();
                mChronometer.setBase(SystemClock.elapsedRealtime());
                start.setText("開始");
                isFirst = false;
                isStart = false;

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}

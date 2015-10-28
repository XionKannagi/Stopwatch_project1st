package com.example.togane.stopwatch_project1st;

import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    //Count mTime;
    Button start,stop,reset;
    private long stopTime;
    private long startTime = 0;
    private boolean isFirst = false;
    private boolean isStart = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTime b = (Count)findViewById(R.id.Time);
        start = (Button)findViewById(R.id.start);
        reset = (Button)findViewById(R.id.reset);

        start.setOnClickListener(new OnClickListener(){
            @Override
             public void onClick(View view) {
                  if(!isStart){
                     if (!isFirst){
                        /*mTime.setBase(SystemClock.elapsedRealtime());
                        startTime = SystemClock.elapsedRealtime();
                        mTime.start();*/
                        isFirst=true;
                     }
                     else{
                        /*long time1 = stopTime - startTime;
                        long time2 = SystemClock.elapsedRealtime() - time1;
                        mTime.setBase(time2);
                        mTime.start();
                        startTime=time2;*/
                     }
                    isStart=true;
                    start.setText("ストップ");
                  }
                  else if (isStart){
                    /*stopTime = SystemClock.elapsedRealtime();
                    mTime.stop();*/
                    isStart=false;
                    start.setText("開始");
                  }
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

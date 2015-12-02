package com.example.togane.stopwatch_project1st;


import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.TimeZone;



public class MainActivity extends ActionBarActivity {

    //Chronometer mChronometer;
    TextView mTimer;
    Button start,stop,reset;
    private long stopTime,tmpTime;
    private long startTime = 0;
    private boolean isFirst = false;
    private boolean isStart = false;
    private LoopEngine loopEngine = new LoopEngine();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mChronometer = (Chronometer)findViewById(R.id.chronometer);
        mTimer =(TextView)findViewById(R.id.timecount);
        start = (Button)findViewById(R.id.start);
        reset = (Button)findViewById(R.id.reset);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isStart) {
                    if (!isFirst) {
                        //mChronometer.setBase(SystemClock.elapsedRealtime());
                        startTime = System.currentTimeMillis();
                        //mChronometer.start();
                        loopEngine.start();
                        tmpTime = System.currentTimeMillis()-startTime;

                        isFirst = true;
                    } else {
                        long time1 = stopTime - startTime;
                        long time2 = System.currentTimeMillis() - time1;
                        //mChronometer.setBase(time2);
                        //mChronometer.start();
                        loopEngine.start();
                        startTime = time2;
                    }
                    isStart = true;
                    start.setText("ストップ");
                } else if (isStart) {

                    stopTime = System.currentTimeMillis();
                    loopEngine.stop();
                    //stopTime = SystemClock.elapsedRealtime();
                    //mChronometer.stop();
                    isStart = false;
                    start.setText("開始");
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                loopEngine.stop();
                mTimer.setText("00:00:00.000");
                //mChronometer.stop();
                //mChronometer.setBase(SystemClock.elapsedRealtime());
                start.setText("開始");
                isFirst = false;
                isStart = false;

            }
        });
    }
    //ミリ秒単位で計測を可能にする
    public void Uptdate(){

        SimpleDateFormat date = new SimpleDateFormat("hh:mm:ss.SSS");
        //タイムゾーン指定でズレがなくせるらしい
        date.setTimeZone(TimeZone.getTimeZone("GMT"));

        long nowTime = System.currentTimeMillis();
        long time = (System.currentTimeMillis()-startTime)+tmpTime;
        mTimer.setText(date.format(time));

    }


    public class LoopEngine extends Handler{
        private boolean isUpdate;

        public void start(){

            this.isUpdate = true;
            handleMessage(new Message());

        }

        public void stop(){

            this.isUpdate = false;

        }

        @Override
        public void handleMessage(Message msg){
            this.removeMessages(0);
            if(this.isUpdate){
                MainActivity.this.Uptdate();
                sendMessageDelayed(obtainMessage(0),1);
            }



        }

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

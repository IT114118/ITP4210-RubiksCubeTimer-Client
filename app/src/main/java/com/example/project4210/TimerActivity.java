package com.example.project4210;

import android.os.Handler;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {

    private static Chronometer chronometer_timer;
    private String[] rotations = {
            "U","D","R","L","F","B",
            "U'","D'","R'","L'","F'", "B'",
            "U2","D2","R2","L2","F2","B2"
    };

    private TextView tv_scramble;
    private TextView timeTv;

    private String time;

    private boolean chronometer_static = false;
    static Handler timeHandler = new Handler();
    private int counttime;


    private ConstraintLayout layout_timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        layout_timer = findViewById(R.id.layout_timer);
        tv_scramble = findViewById(R.id.tv_scramble);
        //tv_scramble.setText(generateScramble(20));
        tv_scramble.setText(createScramble(20));
        timeTv= findViewById(R.id.testTv);
        final Handler handler=  new Handler();


        chronometer_timer = findViewById(R.id.chronometer_timer);

//NOT FINISH
        layout_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeTv.setText("00"+":"+"00"+ ":"+ "00");
                Runnable r = new Runnable() {
                    public void run() {
                        counttime += 1;
                        int mill = counttime%100;
                        int s = counttime/100;
                        int min = s/60;
                        int millstring = mill;
                        int ss = s - min*60;
                        int mm = min;
                        time = mm+":"+ss+ ":"+ millstring;
                        timeTv.setText(time);
                        handler.postDelayed(this,1);
                    }
                };

                if (chronometer_static == false){
                    handler.postDelayed(r,1);
                    chronometer_static = true;

                } else if(chronometer_static == true){
                    handler.removeCallbacks(r);
                    chronometer_static = false;
                }
            }
        });



    }

    private static void startChronometer(){
        chronometer_timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
            @Override
            public void onChronometerTick(Chronometer cArg) {
                long time = SystemClock.elapsedRealtime() - cArg.getBase();
                int h   = (int)(time /3600000);
                int m = (int)(time - h*3600000)/60000;
                int s= (int)(time - h*3600000- m*60000)/1000 ;
                int mill = (int)(time%1000);
                String mm = m < 10 ? "0"+m: m+"";
                String ss = s < 10 ? "0"+s: s+"";
                String millstring = null;
                if (mill < 10){
                    millstring = "000"+ mill;
                }else if(mill < 100){
                    millstring = "00"+ mill;
                }else if (mill < 1000){
                    millstring = "0"+ mill;
                }else {
                    millstring= mill+"";
                }
                cArg.setText(mm+":"+ss+ ":"+ millstring);
            }
        });
        chronometer_timer.setBase(SystemClock.elapsedRealtime());
        chronometer_timer.start();

    }


    //function to generate a Rubik's cube scramble without duplicating or useless steps
    private String generateScramble(int length){
        String scramble = "";
        String[] scrambleArray = new String[length];
        boolean isGoodScramble;

        scrambleArray[0] = rotations[(int)(Math.random() * rotations.length)];
        for (int i = 1; i < length; i++) {
            scrambleArray[i] = rotations[(int)(Math.random() * rotations.length)];

            //check if the rotation is same with the previous rotation
            do {
                if (scrambleArray[i].charAt(0) == scrambleArray[i - 1].charAt(0)) {
                    isGoodScramble = false;
                    scrambleArray[i] = rotations[(int)(Math.random() * rotations.length)];
                } else {
                    isGoodScramble = true;
                }
            } while (!isGoodScramble);

        }

        //set scrambleArray to a String
        for (String s : scrambleArray) {
            scramble += s + " ";
        }

        return scramble;
    }

    private String createScramble(int len) {
        String[] scramble = new String[len];
        for (int i = 0; i < len; i++) {
            int rand = (int)(Math.random() * rotations.length);
            scramble[i] = rotations[rand];
            if (i > 0 && scramble[i].charAt(0) == scramble[i-1].charAt(0)) {
                i--;
            }
        }

        String str = "";
        for (String s : scramble) {
            str += s + " ";
        }

        return str;
    }


}
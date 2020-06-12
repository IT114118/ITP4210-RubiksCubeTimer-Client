package com.example.project4210;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project4210.handler.RecordHandler;
import com.example.project4210.models.RecordModel;

import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {

    private static TextView chronometer_timer;
    private String[] rotations = {
            "U","D","R","L","F","B",
            "U'","D'","R'","L'","F'", "B'",
            "U2","D2","R2","L2","F2","B2"
    };

    private TextView tv_scramble;
    private String scramble;

    private String time;
    static Handler timeHandler = new Handler();
    private int counttime;
    private float get_time;

    private ConstraintLayout layout_timer;
    private boolean chronometer_static = false;
    private int get_mm, get_ss, get_mill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        layout_timer = findViewById(R.id.layout_timer);
        tv_scramble = findViewById(R.id.tv_scramble);
        //tv_scramble.setText(generateScramble(20));
        scramble = createScramble(20);
        tv_scramble.setText(scramble);
        final Handler handler =  new Handler();


        chronometer_timer = findViewById(R.id.chronometer_timer);

        final Runnable r = new Runnable() {
            public void run() {
                counttime += 1;
                int mill = counttime%100;
                int s = counttime/100;
                int min = s/60;
                int millstring = mill;
                int mm = min;
                int ss = s - min*60;
                get_mm = mm;
                get_ss = ss;
                get_mill = mill;
                time = mm+":"+ss+ ":"+ millstring;
                chronometer_timer.setText(time);
                handler.postDelayed(this,0);
            }
        };

        layout_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (chronometer_static == false){
                    counttime = 0;
                    handler.postDelayed(r,0);
                    chronometer_static = true;

                } else if(chronometer_static == true){
                    handler.removeCallbacks(r);
                    get_time =(float)(get_mm*60 + get_ss + ((float)get_mill/100));
                    chronometer_static = false;
                    alertSaveRecord();
                }
            }
        });



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


    private void alertSaveRecord() {
        //create new Alert
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        //set alert properties
        alert.setTitle(String.valueOf(get_time));
        alert.setMessage("Do you want to save this record?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RecordHandler recordHandler = new RecordHandler(TimerActivity.this);
                recordHandler.addRecord(new RecordModel(get_time, scramble, false));
            }
        });
        alert.setNegativeButton("No, delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        //show Alert
        alert.create().show();
    }
}
package com.example.project4210;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.project4210.handler.RecordHandler;
import com.example.project4210.models.RecordModel;


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
    private float counttime;
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
        scramble = createScramble(20);
        tv_scramble.setText(scramble);
        final Handler handler =  new Handler();
        chronometer_timer = findViewById(R.id.chronometer_timer);

        final Runnable r = new Runnable() {
            public void run() {
                counttime += 1.898999;
                get_mill = (int)(counttime % 100);
                int s = (int)(counttime / 100);
                get_mm = s / 60;
                get_ss = s - get_mm*60;
                time = ((get_mm < 10) ? "0" : "") + get_mm + ":" + ((get_ss < 10) ? "0" : "") + get_ss + ":" + ((get_mill < 10) ? "0" : "") + get_mill;
                chronometer_timer.setText(time);
                handler.postDelayed(this,1);
            }
        };

        layout_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!chronometer_static) {
                    counttime = 0;
                    handler.postDelayed(r,1);
                    chronometer_static = true;
                } else {
                    handler.removeCallbacks(r);
                    get_time = (float)(get_mm*60 + get_ss + ((float)get_mill/100));
                    chronometer_static = false;
                    alertSaveRecord();
                }
            }
        });
    }

    private String createScramble(int len) {
        String[] scramble = new String[len];
        for (int i = 0; i < len; i++) {
            scramble[i] = rotations[(int)(Math.random() * rotations.length)];
            if (i > 0 && scramble[i].charAt(0) == scramble[i-1].charAt(0)) { i--; }
        }

        StringBuilder str = new StringBuilder();
        for (String s : scramble) { str.append(s).append(" "); }
        return str.toString();
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
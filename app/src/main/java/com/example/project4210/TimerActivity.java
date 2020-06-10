package com.example.project4210;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.Random;

public class TimerActivity extends AppCompatActivity {

    private String[] rotations = {
            "U","D","R","L","F","B",
            "U'","D'","R'","L'","F'",
            "U2","D2","R2","L2","F2"
    };

    private TextView tv_scramble;

    private Chronometer chronometer_timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        tv_scramble = findViewById(R.id.tv_scramble);
        tv_scramble.setText(generateScramble(20));

        chronometer_timer = findViewById(R.id.chronometer_timer);

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
}
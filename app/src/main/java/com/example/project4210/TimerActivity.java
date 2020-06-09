package com.example.project4210;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class TimerActivity extends AppCompatActivity {

    private String[] rotations = {
            "U","D","R","L","F","B",
            "U'","D'","R'","L'","F'",
            "U2","D2","R2","L2","F2"
    };

    private TextView tv_scramble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        tv_scramble = findViewById(R.id.tv_scramble);
        tv_scramble.setText(generateScramble(20));
    }

    private String generateScramble(int length){
        String scramble = "";
        for (int i = length; i <= length; i++) {
            scramble += " " + rotations[(int)Math.random()*rotations.length].toString();
        }
        return scramble;
    }
}
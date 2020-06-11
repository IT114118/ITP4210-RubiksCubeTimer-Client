package com.example.project4210;

import android.content.Intent;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_timer, btn_Settings, btn_algorithm, btn_record, btn_login;
    TextView et_username, et_rank, et_personalBest, et_avg, et_recentPerformance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //id linking with xml
        btn_timer = findViewById(R.id.btn_timer);
        btn_record = findViewById(R.id.btn_record);
        btn_algorithm = findViewById(R.id.btn_algorithm);
        btn_Settings = findViewById(R.id.btn_settings);
        btn_login = findViewById(R.id.btn_login);

        btn_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TimerActivity.class);
                startActivity(i);
            }
        });

        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RecordActivity.class);
                startActivity(i);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, UserActivity.class);
                startActivity(i);
            }
        });

        btn_algorithm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AlgorithmActivity.class);
                startActivity(i);
            }
        });

        et_username = findViewById(R.id.et_username);
        //et_rank = findViewById(R.id.et_rank);
        et_username = findViewById(R.id.et_username);
        et_username = findViewById(R.id.et_username);

    }
}

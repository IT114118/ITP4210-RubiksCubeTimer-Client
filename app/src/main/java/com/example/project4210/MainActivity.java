package com.example.project4210;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project4210.models.UserModel;

public class MainActivity extends AppCompatActivity {

    Button btn_timer, btn_Settings, btn_algorithm, btn_record;
    TextView tv_username, tv_rank, tv_personalBest, tv_average, tv_recentPerformance;
    UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkID();
        setUp();
    }

    private void linkID(){
        //id linking with xml
        btn_timer = findViewById(R.id.btn_timer);
        btn_record = findViewById(R.id.btn_record);
        btn_algorithm = findViewById(R.id.btn_algorithm);
        btn_Settings = findViewById(R.id.btn_settings);

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

        btn_algorithm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AlgorithmActivity.class);
                startActivity(i);
            }
        });

        tv_username = findViewById(R.id.tv_username);
        tv_rank = findViewById(R.id.tv_rank);
        tv_personalBest = findViewById(R.id.tv_personalBest);
        tv_average = findViewById(R.id.tv_average);
        tv_recentPerformance = findViewById(R.id.tv_rank);

    }

    private boolean checkUserLogin () {
        //TODO Get Username from local database or sharedPreference,
        // and pass to web server to check if user exist,
        // if yes, return true
        return false;
    }

    private UserModel getUserData () {
        //TODO Get Username from local database or sharedPreference,
        // and pass to web server to check if user exist,
        // if yes, return UserModel Data
        UserModel userModel = null;
        return user;
    }

    private void setUp(){
        if (checkUserLogin()) {
            user = getUserData();
            tv_username.setText(user.getName());
            tv_personalBest.setText(String.valueOf(user.getPersonalBest()));
            tv_average.setText(String.valueOf(user.getAverage()));
            tv_rank.setText(user.getGlobalRank());

        } else {
            alertLogin();
        }
    }

    private void alertLogin(){
        //create new Alert
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        //set alert properties
        alert.setTitle("Not Signed In");
        alert.setMessage("Would you like to sign in/up?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //show Alert
        alert.create().show();
    }
}

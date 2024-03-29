package com.example.project4210;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project4210.handler.RecordHandler;
import com.example.project4210.handler.UserHandler;
import com.example.project4210.models.RecordModel;
import com.example.project4210.models.UserModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageButton imgBtn_timer, imgBtn_settings, imgBtn_algorithm, imgBtn_record;
    TextView tv_username, tv_rank, tv_personalBest, tv_average, tv_recentPerformance;
    UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkID();
        if (!setUp()) {
            alertLogin();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        setUp();
    }

    private void linkID() {
        //id linking with xml
        imgBtn_timer = findViewById(R.id.imgBtn_timer);
        imgBtn_record = findViewById(R.id.imgBtn_record);
        imgBtn_algorithm = findViewById(R.id.imgBtn_algorithm);
        imgBtn_settings = findViewById(R.id.imgBtn_settings);

        imgBtn_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TimerActivity.class);
                startActivity(i);
            }
        });

        imgBtn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RecordActivity.class);
                startActivity(i);
            }
        });

        imgBtn_algorithm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LeaderboardActivity.class);
                startActivity(i);
            }
        });

        imgBtn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });

        tv_username = findViewById(R.id.tv_username);
        tv_rank = findViewById(R.id.tv_rank);
        tv_personalBest = findViewById(R.id.tv_personalBest);
        tv_average = findViewById(R.id.tv_average);
        tv_recentPerformance = findViewById(R.id.tv_rank);
    }

    private boolean checkUserLogin() {
        // Get username and token from SharedPreferences
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Account", 0);
        String username = pref.getString("username", null);
        String token = pref.getString("token", null);
        if (username == null || token == null) {
            return false;
        }

        // Check is the token valid
        UserHandler userHandler = new UserHandler();
        if (userHandler.checkToken(username, token)) {
            pref.edit().putString("username", username).apply();
            pref.edit().putString("token", userHandler.getToken()).apply();
            return true;
        }

        Toast.makeText(MainActivity.this, userHandler.getError(), Toast.LENGTH_SHORT).show();
        return false;
    }

    private UserModel getUserData() {
        // Get username and token from SharedPreferences
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Account", 0);
        String username = pref.getString("username", null);
        String token = pref.getString("token", null);

        // Get records from SQLite
        RecordHandler recordHandler = new RecordHandler(this);
        List<RecordModel> records = recordHandler.getAllRecords();

        // Set up UserModel
        UserModel userModel = new UserModel(username);
        userModel.setPersonalBest(RecordActivity.getPersonalBest(records));
        userModel.setAverage(RecordActivity.getAverage(records));

        // Get Global Rank from Web server with token
        UserHandler userHandler = new UserHandler();
        userModel.setGlobalRank(userHandler.getGlobalRank(username, token, userModel.getAverage()));

        return userModel;
    }

    private boolean setUp() {
        if (checkUserLogin()) {
            user = getUserData();
            tv_username.setText(user.getUsername());
            tv_personalBest.setText(RecordActivity.getDisplay(user.getPersonalBest()));
            tv_average.setText(RecordActivity.getDisplay(user.getAverage()));
            int rank = user.getGlobalRank();
            tv_rank.setText((rank <= 0) ? "-" : String.valueOf(rank));
            return true;
        }
        return false;
    }

    private void alertLogin() {
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
        alert.setNegativeButton("No", null);
        //show Alert
        alert.create().show();
    }
}

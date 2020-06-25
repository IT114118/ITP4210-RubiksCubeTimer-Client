package com.example.project4210;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity {

    Button button_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        button_logout = findViewById(R.id.button_logout);
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
                Intent i = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void logout() {
        // Clear SharedPreferences Account Data
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Account", 0);
        pref.edit().remove("username").apply();
        pref.edit().remove("token").apply();
    }
}
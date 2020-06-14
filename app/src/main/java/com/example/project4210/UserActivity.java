package com.example.project4210;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project4210.handler.UserHandler;

public class UserActivity extends AppCompatActivity {

    //id for activity_user

    //id for activity_login
    private EditText et_username, et_password;
    private Button btn_signin;
    private TextView tv_signup_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (checkUserLogin()) {
            // Redirect to MainActivity
            Intent i = new Intent(UserActivity.this, MainActivity.class);
            startActivity(i);

            //go to user page
            //setContentView(R.layout.activity_user);
        } else {
            //go to login page
            setContentView(R.layout.activity_login);
            et_username = findViewById(R.id.et_username);
            et_password = findViewById(R.id.et_password);
            btn_signin = findViewById(R.id.btn_signin);
            tv_signup_intent = findViewById(R.id.tv_signup_intent);

            btn_signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean result = signInUser(et_username.getText().toString(), et_password.getText().toString());
                    if (result) {
                        Toast.makeText(UserActivity.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                        // Redirect to MainActivity
                        Intent i = new Intent(UserActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                }
            });

            tv_signup_intent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //redirect with intent to activity_register
                    Intent intent = new Intent(UserActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

    private boolean checkUserLogin() {

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

        Toast.makeText(UserActivity.this, userHandler.getError(), Toast.LENGTH_SHORT).show();
        return false;
    }


    private boolean signInUser(String username, String password) {
        // Get username and password and send to web server for logging in new existing user
        UserHandler userHandler = new UserHandler();
        if (userHandler.login(username, password)) {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("Account", 0);
            pref.edit().putString("username", username).apply();
            pref.edit().putString("token", userHandler.getToken()).apply();
            return true;
        }

        Toast.makeText(UserActivity.this, userHandler.getError(), Toast.LENGTH_SHORT).show();
        return false;
    }
}
package com.example.project4210;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
            //go to user page
            setContentView(R.layout.activity_user);
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
                    }
                }
            });

            tv_signup_intent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO redirect with intent to activity_register
                }
            });
        }

    }

    private boolean checkUserLogin () {
        //TODO Get Username from local database or sharedPreference,
        // and pass to web server to check if user exist,
        // if yes, return true
        return false;
    }


    private boolean signInUser(String username, String password) {
        //TODO Get username and password and send to web server for logging in new existing user,
        // return true if success.
        return false;
    }
}
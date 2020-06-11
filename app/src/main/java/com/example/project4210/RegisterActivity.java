package com.example.project4210;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_username, et_password, et_confirmPassword;
    private Button btn_signUp;

    String errorString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_confirmPassword = findViewById(R.id.et_confirmPassword);
        btn_signUp = findViewById(R.id.btn_signUp);

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkUserName(et_username.getText().toString()) && checkPassword(et_password.getText().toString(), et_confirmPassword.getText().toString())) {
                    boolean result = registerUser(et_username.getText().toString(), et_password.getText().toString());
                    if (result) {
                        Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Please Check " + errorString, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkUserName(String username) {
        if (username.length() >= 2  && username.length() <= 20) {
            //TODO Check username on web server if it is available

            //TODO please include errorString;
            errorString = "Username has been Used";
            return false;
        } else {
            errorString = "Username too short or too long";
            return false;
        }
    }

    private boolean checkPassword(String password, String confirmPassword) {

        if (password == confirmPassword) {
            return true;
        } else {
            errorString = "Password, Incorrect Password";
            return false;
        }
    }

    private boolean registerUser(String username, String password) {
        //TODO Get username and password and send to web server for creating new user,
        // return true if success.
        return false;
    }
}
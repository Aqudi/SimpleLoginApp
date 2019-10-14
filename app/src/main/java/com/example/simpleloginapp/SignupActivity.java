package com.example.simpleloginapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    TextView username;
    TextView password;
    TextView passwordCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        Button signUpBtn = (Button) findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                username = (TextView) findViewById(R.id.username);
                password = (TextView) findViewById(R.id.password);
                passwordCheck = (TextView) findViewById(R.id.passwordCheck);

                if (username.getText().toString().isEmpty()) {
                    username.setError("Username is required!");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Password is required!");
                } else if (passwordCheck.getText().toString().isEmpty()) {
                    passwordCheck.setError("Password Check is required!");
                } else {

                }
            }
        });
    }
}

package com.example.simpleloginapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.JsonWriter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import static com.example.simpleloginapp.LoginActivity.userArray;


public class SignupActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText password2;
    EditText realName;
    EditText phoneNumber;
    EditText address;

    RadioGroup radioGroup;
    RadioButton accept;
    RadioButton decline;

    Toast toast;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        android.util.Log.d("open Activity", "Signup Activity 시작");

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        accept = (RadioButton) findViewById(R.id.accept);
        decline = (RadioButton) findViewById(R.id.decline);
        decline.setChecked(true);

        Button signUpBtn = (Button) findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                android.util.Log.d("signup", "회원가입 시도");

                username = (EditText) findViewById(R.id.username);
                password = (EditText) findViewById(R.id.password);
                password2 = (EditText) findViewById(R.id.passwordCheck);
                realName = findViewById(R.id.realName);
                phoneNumber = findViewById(R.id.phoneNumber);
                address = findViewById(R.id.address);

                android.util.Log.d("signup", "instance setting");
                if (username.getText().toString().isEmpty()) {
                    username.setError("Username is required!");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Password is required!");
                } else if (password2.getText().toString().isEmpty()) {
                    password2.setError("Password Check is required!");
                } else if (realName.getText().toString().isEmpty()) {
                    realName.setError("Name is required!");
                } else if (phoneNumber.getText().toString().isEmpty()) {
                    phoneNumber.setError("Phone number is required!");
                } else if (address.getText().toString().isEmpty()) {
                    address.setError("Address is required!");
                } else if (!accept.isChecked()) {
                    decline.setError("Accept is required!");
                } else if (password.length() < 10) {
                    password.setError("너무 짧습니다. 10자 이상으로 만드십시오.");
                } else if (!password.getText().toString().equals(password2.getText().toString())) {
                    password2.setError("일치하지 않습니다.");
                } else {
                    android.util.Log.d("signup", "All fields are good");
                    try {
                        boolean unique = true;
                        Iterator<String> itor = userArray.keys();
                        while (itor.hasNext()) {
                            if (itor.next().equals(username.getText().toString())) {
                                unique = false;
                                break;
                            }
                        }

                        if (unique) {
                            toast = Toast.makeText(getApplicationContext(), "회원 가입 성공", Toast.LENGTH_LONG);
                            view = toast.getView();
                            view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSuccess));
                            toast.show();

                            android.util.Log.d("signup", "유저 정보 저장");
                            JSONObject json = new JSONObject();
                            json.put("password", password.getText().toString());
                            json.put("realName", realName.getText().toString());
                            json.put("address", address.getText().toString());
                            json.put("phoneNumber", phoneNumber.getText().toString());
                            userArray.put(username.getText().toString(), json);
                            android.util.Log.d("userData", userArray.toString());

                            finish();
                        } else {
                            toast = Toast.makeText(getApplicationContext(), "중복된 아이디가 있습니다.", Toast.LENGTH_LONG);
                            view = toast.getView();
                            view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorFail));
                            toast.show();
                            username.setError("중복된 아이디가 있습니다.");
                        }

                        resetEditTexts();

                    } catch (org.json.JSONException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetEditTexts();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void resetEditTexts() {
        username.setText("");
        password.setText("");
        password2.setText("");
        realName.setText("");
        phoneNumber.setText("");
        address.setText("");
    }

}

package com.example.simpleloginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class LoginActivity extends AppCompatActivity {

    TextView username;
    TextView password;

    Toast toast;
    View view;

    static JSONObject userArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        try {
            userArray = new JSONObject(loadJSONFromAsset());
        } catch (org.json.JSONException ex) {
            ex.printStackTrace();
        }

        Button signUpBtn = findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 동작 코드 삽입
                username = findViewById(R.id.username);
                password = findViewById(R.id.password);

                if (username.getText().toString().isEmpty()) {
                    username.setError("Username is required!");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Password is required!");
                } else {
                    String errorMSG = "";

                    // 로그인 검사
                    android.util.Log.d("loginTry", "로그인 시도");
                    if (userArray != null) {
                        String name = username.getText().toString();
                        String pass = password.getText().toString();
                        boolean success = false;

                        try {
                            JSONObject obj = userArray.getJSONObject(name);
                            if (obj.getString("password").equals(pass)) {
                                success = true;
                            } else {
                                errorMSG = "비밀번호가 다릅니다.";
                            }

                        } catch (org.json.JSONException ex) {
                            errorMSG = "찾는 아이디가 없습니다.";
                            ex.printStackTrace();
                        }
                        if (success) {
                            String successMSG = "로그인 성공";
                            android.util.Log.d("loginSuccess", successMSG);
                            toast = Toast.makeText(getApplicationContext(), successMSG, Toast.LENGTH_LONG);
                            view = toast.getView();
                            view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSuccess));
                            toast.show();
                            resetEditTexts();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        errorMSG = "로그인 정보 없음";
                        android.util.Log.d("loginFail", "로그인 정보 없음");
                    }
                    if (!errorMSG.equals("")) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                        toast = Toast.makeText(getApplicationContext(), errorMSG, Toast.LENGTH_LONG);
                        view = toast.getView();
                        view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), (R.color.colorFail)));
                        toast.show();
                    }
                    resetEditTexts();
                }
            }
        });
    }

    public String loadJSONFromAsset() {
        android.util.Log.d("file open", "JSON 파일 입력");
        String json = "";
        try {
            InputStream is = getAssets().open("precious_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            int read = is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
            android.util.Log.d("file content", json);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void resetEditTexts() {
        username.setText("");
        password.setText("");
    }
}

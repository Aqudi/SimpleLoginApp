package com.example.simpleloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class LoginActivity extends AppCompatActivity {

    TextView username;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        String errorMSG = "";

        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 동작 코드 삽입
                JSONArray userArray = null;
                try {
                    userArray = new JSONArray(loadJSONFromAsset());
                } catch (org.json.JSONException ex) {
                    ex.printStackTrace();
                }
                // 로그인 검사
                android.util.Log.d("loginInfo", "로그인 시도");
                if (userArray != null) {
                    username = (TextView) findViewById(R.id.username);
                    password = (TextView) findViewById(R.id.password);
                    String name = username.toString();
                    String pass = password.toString();
                    boolean success = false;
                    try {
                        for (int i = 0; i < userArray.length(); i++) {
                            JSONObject order = userArray.getJSONObject(i);
                            if (order.getString("username").equals(name)) {
                                if (order.getString("password").equals(pass)) {
                                    success = true;
                                } else {
                                    android.util.Log.d("loginFail", "비밀번호가 다릅니다.");
                                }
                            }
                        }
                    } catch (org.json.JSONException ex) {
                        ex.printStackTrace();
                    }
                    if (success) {
                        android.util.Log.d("loginSuccess", "로그인 성공");
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        android.util.Log.d("loginFail", "찾는 아이디가 없습니다.");
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                    }
                } else {
                    android.util.Log.d("loginFail", "로그인 정보 없음");
                    // 화면을 깜빡이지 않고 Activity를 재시작하는 코드
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }
            }
        });
    }

    public String loadJSONFromAsset() {
        android.util.Log.d("file open", "JSON 파일 입력");
        String json = null;
        try {
            InputStream is = getAssets().open("precious_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}

package com.example.simpleloginapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        Button naver = (Button) findViewById(R.id.b1);
        naver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.naver.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        Button daum = (Button) findViewById(R.id.b2);
        daum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.daum.net");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        Button google = (Button) findViewById(R.id.b3);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.google.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        Button bing = (Button) findViewById(R.id.b4);
        bing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.bing.com/?toWww=1&redig=6FAD2AA823FA408BB72B60768B1D267A");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        Button Yahoo = (Button) findViewById(R.id.b5);
        Yahoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.yahoo.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        Button duckdcukgo = (Button) findViewById(R.id.b6);
        duckdcukgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://duckduckgo.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        final ToggleButton tb2 =
                (ToggleButton) this.findViewById(R.id.toggleButton2);
        tb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb2.isChecked()) {
                    tb2.setBackgroundDrawable(
                            getApplicationContext().getDrawable(R.drawable.yellow_bird)
                    );
                } else {
                    tb2.setBackgroundDrawable(
                            getApplicationContext().getDrawable(R.drawable.red_bird)
                    );
                }
            }
        });

    }
}

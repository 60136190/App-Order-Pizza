package com.example.oderapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oderapp.R;


public class FirstScreenActivity extends AppCompatActivity {
    protected boolean _active = true;
    protected int _splashTime = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
                Intent i3 = new Intent(FirstScreenActivity.this, SecondScreenActivity.class);
                startActivity(i3);
            }
        }, _splashTime);
    }

    }
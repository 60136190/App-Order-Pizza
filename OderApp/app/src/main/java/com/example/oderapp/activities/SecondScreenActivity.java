package com.example.oderapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.oderapp.R;

public class SecondScreenActivity extends AppCompatActivity {
    private Button btnSignIn;
    private Button btnSignUp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        initUi();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itSignIn = new Intent(SecondScreenActivity.this,Login.class);
                startActivity(itSignIn);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itSignUp = new Intent(SecondScreenActivity.this,SignUp.class);
                startActivity(itSignUp);
            }
        });
    }

    private void initUi() {
        btnSignIn = findViewById(R.id.btn_sign_in);
        btnSignUp = findViewById(R.id.btn_sign_up);
    }

}

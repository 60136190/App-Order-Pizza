package com.example.oderapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oderapp.R;

public class MainActivity3 extends AppCompatActivity {
Button show;
TextView bun;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        show = findViewById(R.id.show);
        bun = findViewById(R.id.bun);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = getIntent();
                String s1 =  String.valueOf(i.getStringExtra("value"));
                bun.setText(s1);


            }
        });
    }
}
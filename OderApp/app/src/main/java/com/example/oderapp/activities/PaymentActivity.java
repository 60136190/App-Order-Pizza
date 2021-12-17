package com.example.oderapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.oderapp.R;
import com.example.oderapp.fragment.CartFragment;

public class PaymentActivity extends AppCompatActivity {

    private LinearLayout lnAddress;
    private LinearLayout lnMethod;
    private ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initUi();

        lnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itAddress = new Intent(PaymentActivity.this,AddressActivity.class);
                startActivity(itAddress);
            }
        });

        lnMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itMethod = new Intent(PaymentActivity.this,MethodPaymentActivity.class);
                startActivity(itMethod);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void initUi() {
        lnAddress = findViewById(R.id.ln_address);
        lnMethod = findViewById(R.id.ln_method);
        imgBack = findViewById(R.id.img_back);
    }

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }
}
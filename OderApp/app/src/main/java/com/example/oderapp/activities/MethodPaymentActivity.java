package com.example.oderapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.oderapp.R;
import com.example.oderapp.adapters.AddressAdapter;
import com.example.oderapp.adapters.MethodOfPaymentAdappter;
import com.example.oderapp.model.Address;
import com.example.oderapp.model.MethodOfPayment;

import java.util.ArrayList;
import java.util.List;

public class MethodPaymentActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MethodOfPaymentAdappter methodOfPaymentAdappter;
    private ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method_payment);
        initUi();
        mRecyclerView = findViewById(R.id.rcv_method);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        methodOfPaymentAdappter = new MethodOfPaymentAdappter(addMethod());
        mRecyclerView.setAdapter(methodOfPaymentAdappter);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void initUi() {
        imgBack = findViewById(R.id.img_back);
    }
    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }

    private List<MethodOfPayment> addMethod() {
        List<MethodOfPayment> list = new ArrayList<>();
        list.add(new MethodOfPayment(1,"30B duong hien quyen","adsad"));
        list.add(new MethodOfPayment(1,"30B duong hien quyen","adsf"));
        list.add(new MethodOfPayment(1,"30B duong hien quyen","adsf"));
        list.add(new MethodOfPayment(1,"30B duong hien quyen","adsf"));
        list.add(new MethodOfPayment(1,"30B duong hien quyen","adsf"));

        return list;

    }


}
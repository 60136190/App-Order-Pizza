package com.example.oderapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oderapp.R;
import com.example.oderapp.model.Item;


public class DetailActivity extends AppCompatActivity {

    private ImageView imageao;
    private ImageView cart;
    private TextView tvname;
    private TextView tvprice;
    private TextView tvsize;
    private Button btncong;
    private Button btntru;
    private TextView tvsoluong;

    private TextView tvdetail;
    private ImageView imageBack;
    private Button btn_buy_now;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initUI();
        Bundle bundle =getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Item item = (Item) bundle.get("object");
        imageao.setImageResource(item.getImage());
        tvname.setText(item.getName());
        tvprice.setText(item.getPrice());
        tvsize.setText(item.getSize());
        tvdetail.setText(item.getDetail());

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentback = new Intent(DetailActivity.this,MainActivity.class);
                startActivity(intentback);
            }
        });



        int sl =Integer.parseInt(tvsoluong.getText().toString());
        if(sl >= 10){
            btncong.setVisibility(View.INVISIBLE);
            btntru.setVisibility(View.VISIBLE);
        }else if(sl<=1){
            btntru.setVisibility(View.INVISIBLE);
        }else if(sl>=1){
            btncong.setVisibility(View.VISIBLE);
            btntru.setVisibility(View.VISIBLE);
        }
        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int slmoi = Integer.parseInt(tvsoluong.getText().toString())+1;
                if (slmoi > 9){
                    btncong.setVisibility(View.INVISIBLE);
                    btntru.setVisibility(View.VISIBLE);
                    tvsoluong.setText(String.valueOf(slmoi));
                }else{
                    btncong.setVisibility(View.VISIBLE);
                    btntru.setVisibility(View.VISIBLE);
                    tvsoluong.setText(String.valueOf(slmoi));
                }

            }
        });
        btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int slmoi = Integer.parseInt(tvsoluong.getText().toString())-1;
                if (slmoi < 2){
                    btncong.setVisibility(View.VISIBLE);
                    btntru.setVisibility(View.INVISIBLE);
                    tvsoluong.setText(String.valueOf(slmoi));
                }else{
                    btncong.setVisibility(View.VISIBLE);
                    btntru.setVisibility(View.VISIBLE);
                    tvsoluong.setText(String.valueOf(slmoi));
                }

            }
        });



    }

    private void initUI(){
        imageao = findViewById(R.id.img_detail_ao);
        tvname = findViewById(R.id.tv_detail_name);
        tvprice = findViewById(R.id.tv_detail_price);
        tvsize = findViewById(R.id.tv_detail_size);
        cart = findViewById(R.id.img_cart_detail);
        tvdetail = findViewById(R.id.tv_detail);
        imageBack = findViewById(R.id.back);
        btncong = findViewById(R.id.btn_cong);
        btntru = findViewById(R.id.btn_detail_tru);
        tvsoluong = findViewById(R.id.tv_detail_soluong);
        btn_buy_now = findViewById(R.id.btn_buy);
    }

}
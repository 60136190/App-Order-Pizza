package com.example.oderapp.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oderapp.R;
import com.example.oderapp.model.ItemFood;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class DetailActivity extends AppCompatActivity {

    private ImageView imageao;
    private ImageView cart;
    private TextView tvname;
    private TextView tvprice;
    private TextView tvsize;
    private Button btncong;
    private Button btntru;
    private TextView tvsoluong;
    private TextView tvDetailDescription;
    private ImageView imageBack;
    private Button btn_buy_now;
    private ArrayList<ItemFood> mItemFoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initUI();
        Bundle bundle = getIntent().getExtras();
//        if(bundle == null){
//            return;
//        }


//        Intent i = getIntent();
//        ItemPizza myParcelableObject = (ItemPizza) i.getSerializableExtra("dghsga");
//        tvname.setText(myParcelableObject.getTensp());

//
        ItemFood item = (ItemFood) bundle.get("object");
//        ItemPasta itemPasta = (ItemPasta) bundle.get("object");
//        ItemAppertizer itemAppertizer = (ItemAppertizer) bundle.get("object");
//        imageao.setImageResource(Integer.parseInt(item.getHinhanh()));

        Picasso.with(this)
                .load(item.getUrl()).fit().centerInside().into(imageao);
        tvname.setText(item.getTensp());
        tvprice.setText(String.valueOf(item.getGia()));
        tvDetailDescription.setText(item.getChitiet());
        tvsize.setText(item.getSize());

//        Picasso.with(this)
//                .load(itemPasta.getUrl()).fit().centerInside().into(imageao);
//        tvname.setText(itemPasta.getTensp());
//        tvprice.setText(String.valueOf(itemPasta.getGia()));
//        tvDetailDescription.setText(itemPasta.getChitiet());
//        tvsize.setText(itemPasta.getSize());
//
//        Picasso.with(this)
//                .load(itemAppertizer.getUrl()).fit().centerInside().into(imageao);
//        tvname.setText(itemAppertizer.getTensp());
//        tvprice.setText(String.valueOf(itemAppertizer.getGia()));
//        tvDetailDescription.setText(itemAppertizer.getChitiet());
//        tvsize.setText(itemAppertizer.getSize());



//        tvDetailDescription.setText(myParcelableObject.getId());



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
        imageao = findViewById(R.id.img_detail_pizza);
        tvname = findViewById(R.id.tv_detail_name);
        tvprice = findViewById(R.id.tv_detail_price);
        tvsize = findViewById(R.id.tv_detail_size);
        cart = findViewById(R.id.img_cart_detail);
        tvDetailDescription = findViewById(R.id.tv_detail_description);
        imageBack = findViewById(R.id.back);
        btncong = findViewById(R.id.btn_cong);
        btntru = findViewById(R.id.btn_detail_tru);
        tvsoluong = findViewById(R.id.tv_detail_soluong);
        btn_buy_now = findViewById(R.id.btn_buy);
    }

}
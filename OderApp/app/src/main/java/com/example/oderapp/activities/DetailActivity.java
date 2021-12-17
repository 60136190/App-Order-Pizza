package com.example.oderapp.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oderapp.R;
import com.example.oderapp.model.ItemFood;
import com.example.oderapp.model.ResponseBodyDTO;
import com.example.oderapp.utils.Contants;
import com.example.oderapp.utils.StoreUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailActivity extends AppCompatActivity {

    private ImageView imageao;
    private ImageView cart;
    private TextView tvname;
    private TextView tvprice;
    private TextView tvsize;
    ItemFood item;
    private TextView tvDetailDescription;

    private ImageView imageBack;
    private Button btn_buy_now;
    private ArrayList<ItemFood> mItemFoodList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//            Intent i = getIntent();
//            String s =  i.getStringExtra("key");
//            Toast.makeText(DetailActivity.this,"tui nef" + s, Toast.LENGTH_LONG).show();


        initUI();
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            item = (ItemFood) bundle.get("object");
            Picasso.with(this)
                    .load(item.getUrl()).fit().centerInside().into(imageao);
            tvname.setText(item.getTensp());
            tvprice.setText(String.valueOf(item.getGia()));
            tvDetailDescription.setText(item.getChitiet());
            tvsize.setText(item.getSize());
//        tvid.setText(String.valueOf(item.getId()));
        }

        btn_buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBodyDTO> loginResponeCall = ApiClient.getProductService().insertCart(item.getId(),
                        "Bearer " + StoreUtil.get(DetailActivity.this, Contants.accessToken));
                loginResponeCall.enqueue(new Callback<ResponseBodyDTO>() {
                    @Override
                    public void onResponse(Call<ResponseBodyDTO> call, Response<ResponseBodyDTO> response) {
                        Toast.makeText(DetailActivity.this, "Added in cart", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBodyDTO> call, Throwable t) {

                    }
                });
            }
        });

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }


    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }

    private void initUI() {
        imageao = findViewById(R.id.img_detail_pizza);
//        tvid = findViewById(R.id.tv_id);
        tvname = findViewById(R.id.tv_detail_name);
        tvprice = findViewById(R.id.tv_detail_price);
        tvsize = findViewById(R.id.tv_detail_size);
        cart = findViewById(R.id.img_cart_detail);
        tvDetailDescription = findViewById(R.id.tv_detail_description);
        imageBack = findViewById(R.id.back);
        btn_buy_now = findViewById(R.id.btn_buy);

    }

}
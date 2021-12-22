package com.example.oderapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.oderapp.R;
import com.example.oderapp.eventbus.EventBack;
import com.example.oderapp.fragment.CartFragment;
import com.example.oderapp.model.Address;
import com.example.oderapp.model.ItemFood;
import com.example.oderapp.model.response.ResponseBodyAddress;
import com.example.oderapp.utils.Contants;
import com.example.oderapp.utils.StoreUtil;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {

    private LinearLayout lnAddress;
    private LinearLayout lnMethod;
    private ImageView imgBack;
    private TextView tvAddress;
    Address address;

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initUi();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Contants.accessToken, "Bearer " + StoreUtil.get(getApplicationContext(), Contants.accessToken));

        Call<ResponseBodyAddress> responseDTOCall = ApiClient.getService()
                .getAddress(10, hashMap);
        responseDTOCall.enqueue(new Callback<ResponseBodyAddress>() {
            @Override
            public void onResponse(Call<ResponseBodyAddress> call, Response<ResponseBodyAddress> response) {
                // tvAddress.setText(response.body().getData().get(0).getDiachi());
            }

            @Override
            public void onFailure(Call<ResponseBodyAddress> call, Throwable t) {
                t.printStackTrace();
            }
        });


//        Intent iin= getIntent();
//        Bundle b = iin.getExtras();
//
//
//        if(b!=null)
//        {
//            String j =(String) b.get("name");
//            tvAddress.setText(j);
//        }

        lnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itAddress = new Intent(PaymentActivity.this, AddressActivity.class);
                startActivity(itAddress);
            }
        });

        lnMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itMethod = new Intent(PaymentActivity.this, MethodPaymentActivity.class);
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
        lnAddress = findViewById(R.id.ln_choose_address);
        lnMethod = findViewById(R.id.ln_choose_method);
        imgBack = findViewById(R.id.img_back);
        tvAddress = findViewById(R.id.tv_address);
    }

    @Subscribe
    public void event(EventBack eventBack) {
        if (eventBack != null) {
            Log.i("TAG", "event: " + eventBack.getAddress().getId());
            tvAddress.setText(eventBack.getAddress().getId());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 10){
            Address address =(Address) data.getSerializableExtra("item");
            Log.i("TAG", "onActivityResult: 1");
        }

        Log.i("TAG", "onActivityResult: ");
    }
}
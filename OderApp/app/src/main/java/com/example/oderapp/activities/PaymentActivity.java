package com.example.oderapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oderapp.R;
import com.example.oderapp.eventbus.EvenbusAddress;
import com.example.oderapp.eventbus.EvenbusMethodPayment;
import com.example.oderapp.model.Address;
import com.example.oderapp.model.InformationUser;
import com.example.oderapp.model.ItemBill;
import com.example.oderapp.model.MethodOfPayment;
import com.example.oderapp.model.response.ResponseBodyBill;
import com.example.oderapp.model.response.ResponseInformationUser;
import com.example.oderapp.utils.Contants;
import com.example.oderapp.utils.StoreUtil;

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
    private TextView tvIdAddress;
    private TextView tvNameOfMethodOfPayment;
    private TextView tvIdMethod;

    private TextView tvFullName;
    private TextView tvSdt;

    private Button btnBuyNow;
    Address address;
    ItemBill itemBill;
    MethodOfPayment methodOfPayment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initUi();
        getData();

        EventBus.getDefault().register(this);

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


        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(tvIdAddress.getText().toString().trim()) && TextUtils.isEmpty(tvIdMethod.getText().toString().trim()) ) {
                    Toast.makeText(PaymentActivity.this, "Your address or method is blank", Toast.LENGTH_SHORT).show();
                } else {
                    int IdAddress = Integer.parseInt(tvIdAddress.getText().toString());
                    int IdMethod = Integer.parseInt(tvIdMethod.getText().toString());
                    itemBill = new ItemBill(IdMethod, IdAddress);
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put(Contants.contentLength, "<calculated when request is sent>");
                    hashMap.put(Contants.accessToken, "Bearer " + StoreUtil.get(PaymentActivity.this, Contants.accessToken));
                    Call<ResponseBodyBill> responseBodyBillCall = ApiClient.getService().createBill(hashMap, itemBill);

                    responseBodyBillCall.enqueue(new Callback<ResponseBodyBill>() {
                        @Override
                        public void onResponse(Call<ResponseBodyBill> call, Response<ResponseBodyBill> response) {
                        }

                        @Override
                        public void onFailure(Call<ResponseBodyBill> call, Throwable t) {

                        }
                    });
                }
            }
        });


    }

    public void initUi() {
        lnAddress = findViewById(R.id.ln_choose_address);
        lnMethod = findViewById(R.id.ln_choose_method);
        imgBack = findViewById(R.id.img_back);

        tvAddress = findViewById(R.id.tv_address);
        tvIdAddress = findViewById(R.id.tv_id_address);
        tvNameOfMethodOfPayment = findViewById(R.id.tv_name_of_method);
        tvIdMethod = findViewById(R.id.tv_id_method);

        tvFullName = findViewById(R.id.tv_full_name);
        tvSdt = findViewById(R.id.tv_sdt);
        btnBuyNow = findViewById(R.id.btn_buy_now);
    }

    public void getData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Contants.accessToken, "Bearer " + StoreUtil.get(PaymentActivity.this, Contants.accessToken));
        Call<ResponseInformationUser> loginResponeCall = ApiClient.getService().getProfile(hashMap);
        loginResponeCall.enqueue(new Callback<ResponseInformationUser>() {
            @Override
            public void onResponse(Call<ResponseInformationUser> call, Response<ResponseInformationUser> response) {
                InformationUser informationUser = response.body().getData().get(0);
                tvFullName.setText(informationUser.getHoten());
                tvSdt.setText(informationUser.getDienthoai());
            }

            @Override
            public void onFailure(Call<ResponseInformationUser> call, Throwable t) {

            }
        });
    }
    @Subscribe
    public void onEvent(EvenbusMethodPayment event){
        Log.d("hehe","Even" + event.getId() + event.getName());
        tvIdMethod.setText(String.valueOf(event.getId()));
        tvNameOfMethodOfPayment.setText(event.getName());
    }

    @Subscribe
    public void onEvent(EvenbusAddress event){
        Log.d("hehe","Even" + event.getId() + event.getName());
        tvIdAddress.setText(String.valueOf(event.getId()));
        tvAddress.setText(event.getName());
    }

}
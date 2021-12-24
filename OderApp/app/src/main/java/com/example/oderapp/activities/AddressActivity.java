package com.example.oderapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oderapp.R;
import com.example.oderapp.SendData;
import com.example.oderapp.adapters.AddressAdapter;
import com.example.oderapp.adapters.ItemCartAdappter;
import com.example.oderapp.eventbus.EventBack;
import com.example.oderapp.model.Address;
import com.example.oderapp.model.response.ResponseBodyAddress;
import com.example.oderapp.model.response.ResponseBodyCart;
import com.example.oderapp.utils.Contants;
import com.example.oderapp.utils.StoreUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    Address address;

    private ImageView imgBack;
    private ImageView imgAddAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initUi();
        getListAddress();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        imgAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itAddress = new Intent(AddressActivity.this, AddAddressActivity.class);
                startActivity(itAddress);
            }
        });
//
//
    }

    //
    private void initUi() {
        imgBack = findViewById(R.id.img_back);
        imgAddAddress = findViewById(R.id.img_add_address);
        mRecyclerView = findViewById(R.id.rcv_address);
    }

    @Override
    public void onBackPressed() {
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }

    private void getListAddress() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Contants.accessToken, "Bearer " + StoreUtil.get(AddressActivity.this, Contants.accessToken));

        Call<ResponseBodyAddress> responseDTOCall = ApiClient.getService().getListAddress(hashMap);
        responseDTOCall.enqueue(new Callback<ResponseBodyAddress>() {
            @Override
            public void onResponse(Call<ResponseBodyAddress> call, Response<ResponseBodyAddress> response) {
                AddressAdapter adappter = new AddressAdapter(AddressActivity.this, response.body().getData(), new SendData() {
                    @Override
                    public void sendData(Address address) {
                        Intent intent = new Intent();
                        intent.putExtra("keyName", "test");
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                mRecyclerView.setAdapter(adappter);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(AddressActivity.this));

                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(AddressActivity.this, DividerItemDecoration.VERTICAL);
                mRecyclerView.addItemDecoration(itemDecoration);
            }

            @Override
            public void onFailure(Call<ResponseBodyAddress> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
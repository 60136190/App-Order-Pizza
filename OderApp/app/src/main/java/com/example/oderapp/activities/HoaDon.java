package com.example.oderapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oderapp.R;
import com.example.oderapp.adapters.HoaDonAdappter;
import com.example.oderapp.model.ItemHoaDon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class HoaDon extends AppCompatActivity {

    private HoaDonAdappter userAdapter;
    private RecyclerView rcvUser1;
    private List<ItemHoaDon> mListHoaDon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        String data = getIntent().getStringExtra("lista");
        mListHoaDon = new Gson().fromJson(data,new TypeToken<ArrayList<ItemHoaDon>>(){}.getType());
        rcvUser1 = findViewById(R.id.rcv_user2);
        userAdapter = new HoaDonAdappter();
        if (mListHoaDon==null) mListHoaDon = new ArrayList<>();
        mListHoaDon =  UserDatabase.getInstance(this).userDAO().getListItemUser();
        userAdapter.setDataa(mListHoaDon);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUser1.setLayoutManager(linearLayoutManager);
        rcvUser1.setAdapter(userAdapter);



    }

}
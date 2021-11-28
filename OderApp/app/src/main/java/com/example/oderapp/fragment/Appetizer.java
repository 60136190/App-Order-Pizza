package com.example.oderapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.oderapp.R;
import com.example.oderapp.adapters.ItemClothesAdappter;
import com.example.oderapp.model.Item;

import java.util.ArrayList;
import java.util.List;

public class Appetizer extends Fragment {

    private RecyclerView rcvCooking;
    private List<Item> item;
    public Appetizer() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_french_fries, container, false);
        rcvCooking = view.findViewById(R.id.rcv_cooking);
        ItemClothesAdappter itemScfiAdappter = new ItemClothesAdappter(getContext(),item);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rcvCooking.setLayoutManager(gridLayoutManager);
        rcvCooking.setAdapter(itemScfiAdappter);
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        item = new ArrayList<>();
        item.add(new Item(R.drawable.my_cay_hai_san,"TB Capcong Kaki - Beige","15","L","100% cotton"));
        item.add(new Item(R.drawable.my_cay_thit_xong_khoi,"TB Capcong Couduroy - Olive","15","S","Pollyeste"));
        item.add(new Item(R.drawable.my_cay_xuc_xich,"TB Capcong Couduroy - Buff","15","XL","100 cotton"));
        item.add(new Item(R.drawable.my_chay_sot_kem_tuoi,"MILK COFFEE BASEBALL CAP DISTRESSED","15","XL","100 cotton"));
        item.add(new Item(R.drawable.my_chay_sot_marinara,"SUEDE BUCKET HAT","15","XL","100 cotton"));
        item.add(new Item(R.drawable.my_giam_bong_nam_sot_kem,"CODUROY MIKI HAT","15","XL","100 cotton"));
        item.add(new Item(R.drawable.my_thi_bo_bam,"SUEDE BASEBALL CAP","15","XL","100 cotton"));
        item.add(new Item(R.drawable.my_tom_sot_kem_ca_chua,"SUEDE BASEBALL CAP","15","XL","100 cotton"));


    }
}
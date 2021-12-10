package com.example.oderapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.oderapp.R;
import com.example.oderapp.model.Item;

import java.util.List;

public class Salad extends Fragment {
    private RecyclerView rcvCooking;
    private List<Item> item;
    public Salad() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_salad, container, false);
//        rcvCooking = view.findViewById(R.id.rcv_cooking);
//        ItemClothesAdappter itemScfiAdappter = new ItemClothesAdappter(getContext(),item);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
//        rcvCooking.setLayoutManager(gridLayoutManager);
//        rcvCooking.setAdapter(itemScfiAdappter);
        return view;
    }
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        item = new ArrayList<>();
//        item.add(new Item(R.drawable.salad_da_ca_hoi_gion,"TB Capcong Kaki - Beige","15","L","100% cotton"));
//        item.add(new Item(R.drawable.salad_ga_gion_khong_xuong,"TB Capcong Couduroy - Olive","15","S","Pollyeste"));
//        item.add(new Item(R.drawable.salad_tron_dau_giam,"TB Capcong Couduroy - Buff","15","XL","100 cotton"));
//        item.add(new Item(R.drawable.salad_tron_sot_caesar,"MILK COFFEE BASEBALL CAP DISTRESSED","15","XL","100 cotton"));
//
//
//    }
}
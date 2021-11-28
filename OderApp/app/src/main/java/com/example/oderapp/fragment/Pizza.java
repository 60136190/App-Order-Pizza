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

public class Pizza extends Fragment {

    private RecyclerView rcvCooking;
    private List<Item> item;

    public Pizza() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pizza, container, false);
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
        item.add(new Item(R.drawable.pizza_cheese,"TB Capcong Kaki - Beige","15","L","100% cotton"));
        item.add(new Item(R.drawable.pizza_hai_san_dao,"TB Capcong Couduroy - Olive","15","S","Pollyeste"));
        item.add(new Item(R.drawable.pizza_hai_san_pesto_xanh,"TB Capcong Couduroy - Buff","15","XL","100 cotton"));
        item.add(new Item(R.drawable.pizza_nam_loai_thit_dac_biet_va_rau_cu,"MILK COFFEE BASEBALL CAP DISTRESSED","15","XL","100 cotton"));
        item.add(new Item(R.drawable.pizza_rau_cu,"SUEDE BUCKET HAT","15","XL","100 cotton"));
        item.add(new Item(R.drawable.pizza_rau_cu,"CODUROY MIKI HAT","15","XL","100 cotton"));
        item.add(new Item(R.drawable.pizza_xuc_xich_y,"SUEDE BASEBALL CAP","15","XL","100 cotton"));


    }
}
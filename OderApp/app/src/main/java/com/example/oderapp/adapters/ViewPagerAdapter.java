package com.example.oderapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.oderapp.fragment.Appetizer;
import com.example.oderapp.fragment.Drink;
import com.example.oderapp.fragment.Pizza;
import com.example.oderapp.fragment.Salad;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Pizza();
            case 1:
                return new Appetizer();
            case 2:
                return new Drink();
            case 3:
                return new Salad();
            default:
                return new Pizza();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}

package com.example.oderapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.example.oderapp.R;
import com.example.oderapp.adapters.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuFragment extends Fragment {
    private ViewPager mviewPager;
    private BottomNavigationView mnavigationView;
    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        mviewPager = view.findViewById(R.id.viewpager);
        mnavigationView = view.findViewById(R.id.bottom_navigation_top);

        ViewPagerAdapter viewPagerAdapter =new ViewPagerAdapter(getParentFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mviewPager.setAdapter(viewPagerAdapter);
        mviewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mnavigationView.getMenu().findItem(R.id.pizza).setChecked(true);
                    case 1:
                        mnavigationView.getMenu().findItem(R.id.appetizer).setChecked(true);
                    case 2:
                        mnavigationView.getMenu().findItem(R.id.drink).setChecked(true);
                    case 3:
                        mnavigationView.getMenu().findItem(R.id.salad).setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mnavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.pizza:
                        mviewPager.setCurrentItem(0);
                        Pizza fragmentA = (Pizza) mviewPager.getAdapter().instantiateItem(mviewPager,0);
                        break;
                    case R.id.appetizer:
                        mviewPager.setCurrentItem(1);
                        break;
                    case R.id.drink:
                        mviewPager.setCurrentItem(2);
                        break;
                    case R.id.salad:
                        mviewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
        return view;

    }

}
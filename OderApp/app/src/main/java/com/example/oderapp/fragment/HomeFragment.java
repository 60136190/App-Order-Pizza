package com.example.oderapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oderapp.R;
import com.example.oderapp.adapters.HotThisMonthAdapter;
import com.example.oderapp.model.Hot;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ViewFlipper viewFlipper;
    private RoundedImageView roundedImageView;
    TextView tvinfo;

    // Hot this month
    private RecyclerView rcvHotThisMonth;
    private List<Hot> hotThisMonths;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        roundedImageView = view.findViewById(R.id.pizza);
        tvinfo = view.findViewById(R.id.tv_from);


        // hot this month
//
//
//        Bundle bundleRecive = getActivity().getIntent().getExtras();
//        if (bundleRecive != null) {
//            User user = (User) bundleRecive.get("object_user");
//            if (user != null) {
//                tvinfo.setText(user.getName());
//            }
//        }

        int images[] = {R.drawable.promotiona, R.drawable.promotionb, R.drawable.promotionc,
                R.drawable.promotiond, R.drawable.promotione, R.drawable.promotionf, R.drawable
                .promotiong};

        viewFlipper = view.findViewById(R.id.viewflipper_hot_this_month);
//
//        for (int i=0 ; i<images.length; i++){
//            flipperImages(images[i]);
//        }

        for (int image : images) {
            flipperImages(image);
        }

        rcvHotThisMonth = view.findViewById(R.id.rcv_hot_this_month);
        HotThisMonthAdapter hotThisMonthAdapter = new HotThisMonthAdapter(getContext(),hotThisMonths);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rcvHotThisMonth.setLayoutManager(gridLayoutManager);

        rcvHotThisMonth.setAdapter(hotThisMonthAdapter);
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hotThisMonths = new ArrayList<>();
        hotThisMonths.add(new Hot(R.drawable.hota,"Hết hạn 31-12-2021","Bữa ăn đồng giá 39k","10:00 - 23:55","rat la ngon"));
        hotThisMonths.add(new Hot(R.drawable.hotb,"Hết hạn 26-12-2021","Combo no nê giá chỉ 99k","10:00 - 23:55","Một chiếc pizza Double chesse kết với với xúc xích Ý Peperoni, một chai pepsi 1,5L"));
        hotThisMonths.add(new Hot(R.drawable.hotc,"Hết hạn 31-12-2021","Combo pizza Giáng sinh","10:00 - 23:55","Ăn đã đời, vui say sưa"));
        hotThisMonths.add(new Hot(R.drawable.hota,"Hết hạn 31-12-2021","Bua an dong gia 39k","10:00 - 23:55","rat la ngon"));
        hotThisMonths.add(new Hot(R.drawable.hota,"Hết hạn 31-12-2021","Bua an dong gia 39k","10:00 - 23:55","rat la ngon"));

    }

    public void flipperImages(int image) {
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3500);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(getContext(), android.R.anim.slide_in_left);

    }

}
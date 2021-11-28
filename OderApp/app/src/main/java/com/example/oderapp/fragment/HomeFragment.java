package com.example.oderapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;

import com.example.oderapp.R;
import com.example.oderapp.model.User;
import com.makeramen.roundedimageview.RoundedImageView;

public class HomeFragment extends Fragment {
     ViewFlipper viewFlipper;
    private RoundedImageView roundedImageView;
    private TextView tvinfo;
    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        roundedImageView = view.findViewById(R.id.pizza);
        tvinfo = view.findViewById(R.id.info);
        Bundle bundleRecive = getActivity().getIntent().getExtras();
        if (bundleRecive != null){
            User user = (User) bundleRecive.get("object_user");
            if (user != null){
                tvinfo.setText(user.getName());
            }
        }
//        roundedImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext() MainActivity2.class);
//                startActivity(intent);
//            }
//        });
        int images[] = {R.drawable.promotiona, R.drawable.promotionb, R.drawable.promotionc,
                R.drawable.promotiond, R.drawable.promotione, R.drawable.promotionf,R.drawable
        .promotiong};

        viewFlipper = view.findViewById(R.id.viewflipper_hot_this_month);
//
//        for (int i=0 ; i<images.length; i++){
//            flipperImages(images[i]);
//        }

        for (int image: images){
            flipperImages(image);
        }
        return view;
    }
    public void flipperImages(int image){
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3500);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(getContext(), android.R.anim.slide_in_left);

    }
}
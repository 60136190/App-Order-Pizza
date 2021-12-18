package com.example.oderapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.oderapp.R;
import com.example.oderapp.model.MethodOfPayment;

import java.util.List;

public class MethodOfPaymentAdappter extends RecyclerView.Adapter<MethodOfPaymentAdappter.ItemViewHolder> {

    List<MethodOfPayment> mMethodList;
    // filter

    public MethodOfPaymentAdappter(List<MethodOfPayment> mMethodList) {

        this.mMethodList = mMethodList;
    }

    @Override
    public MethodOfPaymentAdappter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_method_of_payment, parent, false);
        return new MethodOfPaymentAdappter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MethodOfPaymentAdappter.ItemViewHolder holder, int position) {

        MethodOfPayment currentItem = mMethodList.get(position);
        int id_nd = currentItem.getId();
        String nameMethod = currentItem.getTen_hinhthuc();
        String urlMethod = currentItem.getUrl();


        holder.tvNameMethod.setText(nameMethod);
        holder.urlMethod.setImageResource(Integer.parseInt(urlMethod));
    }


    @Override
    public int getItemCount() {

        return mMethodList.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameMethod;
        private ImageView urlMethod;


        public ItemViewHolder(View itemView) {
            super(itemView);
            tvNameMethod = itemView.findViewById(R.id.tv_method);
            urlMethod = itemView.findViewById(R.id.img_method);

        }
    }




}

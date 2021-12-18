package com.example.oderapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.oderapp.R;
import com.example.oderapp.activities.AddressActivity;
import com.example.oderapp.model.Address;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ItemViewHolder> {

    List<Address> mAddressList;
    // filter

    public AddressAdapter(AddressActivity addressActivity, List<Address> mAddressList) {

        this.mAddressList = mAddressList;
    }

    @Override
    public AddressAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_address, parent, false);
        return new AddressAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AddressAdapter.ItemViewHolder holder, int position) {

        Address currentItem = mAddressList.get(position);
//        int id_nd = currentItem.getId_nd();
        String diachi = currentItem.getDiachi();


        holder.tvDiaChi.setText(diachi);



    }


    @Override
    public int getItemCount() {

        return mAddressList.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDiaChi;


        public ItemViewHolder(View itemView) {
            super(itemView);
            tvDiaChi = itemView.findViewById(R.id.tv_diaChi);

        }
    }




}

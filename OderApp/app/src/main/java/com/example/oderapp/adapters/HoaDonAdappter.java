package com.example.oderapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oderapp.R;
import com.example.oderapp.model.ItemHoaDon;

import java.util.List;

public class HoaDonAdappter extends RecyclerView.Adapter<HoaDonAdappter.ViewHolder>{

    private List<ItemHoaDon> mListHoaDon;

    public void setDataa(List<ItemHoaDon> list){
        this.mListHoaDon = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoa_don,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemHoaDon itemHoaDon = mListHoaDon.get(position);
        if (itemHoaDon == null){
            return;
        }
        holder.tv_name.setText(itemHoaDon.getName());
        holder.tv_price.setText(itemHoaDon.getPrice());
        holder.tv_size.setText(itemHoaDon.getSize());
    }

    @Override
    public int getItemCount() {
        if(mListHoaDon != null)
        {
            return mListHoaDon.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img_ao;
        TextView tv_name;
        TextView tv_size;
        TextView tv_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_ao = itemView.findViewById(R.id.img_hd_ao);
            tv_name = itemView.findViewById(R.id.tv_hd_name);
            tv_size = itemView.findViewById(R.id.tv_hd_size);
            tv_price = itemView.findViewById(R.id.tv_hd_price);
        }
    }
}

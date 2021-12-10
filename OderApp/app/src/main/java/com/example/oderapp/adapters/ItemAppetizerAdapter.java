package com.example.oderapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oderapp.R;
import com.example.oderapp.activities.DetailActivity;
import com.example.oderapp.fragment.Appetizer;
import com.example.oderapp.model.ItemAppertizer;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemAppetizerAdapter extends RecyclerView.Adapter<ItemAppetizerAdapter.ItemViewHolder>{

    private Context mContext;
    private ArrayList<ItemAppertizer> mItemPizzaList;

    // click item pass Detail Activity
//    private OnItemClickLisener mLisener;
//    public interface OnItemClickLisener{
//        void onItemClick(int position);
//
//    }
//    public void setOnItemClickListener(OnItemClickLisener lisener){
//        mLisener = lisener;
//    }

    public ItemAppetizerAdapter(Context context, ArrayList<ItemAppertizer> item1) {
        mContext = context;
        mItemPizzaList = item1;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_appertizer,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        ItemAppertizer currentItem = mItemPizzaList.get(position);
        if(currentItem == null){
            return;
        }
        String imageUrl = currentItem.getHinhanh();
        String tenSp = currentItem.getTensp();
        int tien = currentItem.getGia();

        Picasso.with(mContext)
                .load(imageUrl).fit().centerInside().into(holder.itemImage);
        holder.itemname.setText(tenSp);
        holder.itemprice.setText(Integer.toString(tien));



//        holder.tvdetail.setText(itemScfi.getDetail());

        holder.layoutItemAppertizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickgotoDetail(currentItem);


                Toast.makeText(mContext,"Hi",Toast.LENGTH_LONG).show();
            }
        });
    }
        private void onClickgotoDetail(ItemAppertizer appetizer) {
        Intent intent = new Intent(mContext, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", (Serializable) appetizer);
        intent.putExtras(bundle);
        mContext.startActivity(intent);

    }
    @Override
    public int getItemCount() {
        return mItemPizzaList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

         ImageView itemImage;
         TextView itemprice;
         TextView itemname;
         LinearLayout layoutItemAppertizer;

        public ItemViewHolder( View itemView) {
            super(itemView);
            layoutItemAppertizer = itemView.findViewById(R.id.layout_item_appertizer);
            itemImage=itemView.findViewById(R.id.itemImage_appertizer);
            itemprice=itemView.findViewById(R.id.tv_price);
            itemname=itemView.findViewById(R.id.tv_name);

        }
    }

}

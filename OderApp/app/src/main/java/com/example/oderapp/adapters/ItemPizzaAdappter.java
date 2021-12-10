package com.example.oderapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oderapp.R;
import com.example.oderapp.activities.DetailActivity;
import com.example.oderapp.fragment.Pizza;
import com.example.oderapp.model.Item;
import com.example.oderapp.model.ItemPizza;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemPizzaAdappter extends RecyclerView.Adapter<ItemPizzaAdappter.ItemViewHolder>{

    private Context mContext;
    private ArrayList<ItemPizza> mItemPizzaList;


    public ItemPizzaAdappter(Context context, ArrayList<ItemPizza> item1) {
        mContext = context;
        mItemPizzaList = item1;
    }

    @Override
    public ItemViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ItemViewHolder holder, int position) {

        ItemPizza currentItem = mItemPizzaList.get(position);

        String imageUrl = currentItem.getUrl();
        String tenSp = currentItem.getTensp();
        int tien = currentItem.getGia();
        Picasso.with(mContext)
                .load(imageUrl).fit().centerInside().into(holder.itemImage);
        holder.itemname.setText(tenSp);
        holder.itemprice.setText(Integer.toString(tien));

//        holder.tvdetail.setText(itemScfi.getDetail());

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onClickgotoDetail(currentItem);

                Intent i = new Intent(mContext,DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object",currentItem);
                i.putExtras( bundle);
                mContext.startActivity(i);
            } 
        });
    }

    private void onClickgotoDetail(ItemPizza pz) {


    }

    @Override
    public int getItemCount() {

            return mItemPizzaList.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemImage;
        public TextView itemprice;
        public TextView itemname;

        LinearLayout layoutItem;
        public ItemViewHolder( View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layout_item);
            itemImage=itemView.findViewById(R.id.itemImage);
            itemprice=itemView.findViewById(R.id.tv_price);
            itemname=itemView.findViewById(R.id.tv_name);
        }
    }

}

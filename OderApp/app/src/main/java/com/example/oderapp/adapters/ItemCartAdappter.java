package com.example.oderapp.adapters;

import android.content.ClipData;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.example.oderapp.R;
import com.example.oderapp.activities.ApiClient;
import com.example.oderapp.model.Currency;
import com.example.oderapp.model.GetItemCart;
import com.example.oderapp.model.ItemCart;
import com.example.oderapp.model.ItemFood;
import com.example.oderapp.model.UserRegister;
import com.example.oderapp.model.response.ResponseBodyCart;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemCartAdappter extends RecyclerView.Adapter<ItemCartAdappter.ItemViewHolder> {

    private Context mContext;
    List<ItemCart> mItemCartList;
    // filter

    public ItemCartAdappter(Context context, List<ItemCart> mItemCartList) {
        this.mContext = context;
        this.mItemCartList = mItemCartList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        ItemCart currentItem = mItemCartList.get(position);

        int id = currentItem.getId();
        int id_nd = currentItem.getId_nd();
        int id_sp = currentItem.getId_sp();
        int dongia = currentItem.getDon_gia();
        int soluong = currentItem.getSoluong();
        int tonggia = currentItem.getTong_gia();

        holder.tvDonGia.setText(Integer.toString(dongia));
        holder.tvSoLuong.setText(Integer.toString(soluong));
        holder.tvTongGia.setText(Integer.toString(tonggia));
        //
        int sl = Integer.parseInt(holder.tvSoLuong.getText().toString());
        if (sl >= 10) {
            holder.btnCong.setVisibility(View.INVISIBLE);
            holder.btnTru.setVisibility(View.VISIBLE);
        } else if (sl <= 1) {
            holder.btnTru.setVisibility(View.INVISIBLE);
        } else if (sl >= 1) {
            holder.btnCong.setVisibility(View.VISIBLE);
            holder.btnTru.setVisibility(View.VISIBLE);
        }
        holder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoi = Integer.parseInt(holder.tvSoLuong.getText().toString()) + 1;
                if (slmoi > 9) {
                    holder.btnCong.setVisibility(View.INVISIBLE);
                    holder.btnTru.setVisibility(View.VISIBLE);
                    holder.tvSoLuong.setText(String.valueOf(slmoi));
                } else {
                    holder.btnCong.setVisibility(View.VISIBLE);
                    holder.btnTru.setVisibility(View.VISIBLE);
                    holder.tvSoLuong.setText(String.valueOf(slmoi));
                }
            }
        });

        holder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoi = Integer.parseInt(holder.tvSoLuong.getText().toString()) - 1;
                if (slmoi < 2) {
                    holder.btnCong.setVisibility(View.VISIBLE);
                    holder.btnTru.setVisibility(View.INVISIBLE);
                    holder.tvSoLuong.setText(String.valueOf(slmoi));
                } else {
                    holder.btnCong.setVisibility(View.VISIBLE);
                    holder.btnTru.setVisibility(View.VISIBLE);
                    holder.tvSoLuong.setText(String.valueOf(slmoi));
                }
            }
        });

        holder.lnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemCartList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

        Call<ItemFood> responseDTOCall = ApiClient.getProductService().getDescription(currentItem.getId_sp());
        responseDTOCall.enqueue(new Callback<ItemFood>() {
            @Override
            public void onResponse(Call<ItemFood> call, Response<ItemFood> response) {
                holder.tv_nameProduct.setText(response.body().getTensp() + "");
            }

            @Override
            public void onFailure(Call<ItemFood> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }


    @Override
    public int getItemCount() {

        return mItemCartList.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgProduct;
        private TextView tvDonGia;
        private TextView tvTongGia;
        private Button btnCong;
        private Button btnTru;
        private TextView tvSoLuong;
        private TextView tv_nameProduct;

        private SwipeRevealLayout swipeRevealLayout;
        private LinearLayout lnDelete;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product);
            tvDonGia = itemView.findViewById(R.id.tv_donGia);
            tvTongGia = itemView.findViewById(R.id.tv_tongGia);
            btnTru = itemView.findViewById(R.id.btn_tru);
            btnCong = itemView.findViewById(R.id.btn_cong);
            tvSoLuong = itemView.findViewById(R.id.tv_soluong);
            tv_nameProduct = itemView.findViewById(R.id.tv_nameProduct);

            lnDelete = itemView.findViewById(R.id.ln_delete);
            swipeRevealLayout = itemView.findViewById(R.id.swipereveallayout);
        }
    }




}

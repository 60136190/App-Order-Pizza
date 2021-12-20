package com.example.oderapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.oderapp.R;
import com.example.oderapp.activities.ApiClient;
import com.example.oderapp.model.ItemBill;
import com.example.oderapp.model.ItemCart;
import com.example.oderapp.model.response.ResponseBodyBill;
import com.example.oderapp.model.response.ResponseBodyCart;
import com.example.oderapp.utils.Contants;
import com.example.oderapp.utils.StoreUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemBillAdapter extends RecyclerView.Adapter<ItemBillAdapter.ItemViewHolder> {

    private Context mContext;
    List<ItemBill> mItemBillList;
    // filter

    public ItemBillAdapter(Context context, List<ItemBill> mItemBillList) {
        this.mContext = context;
        this.mItemBillList = mItemBillList;
    }

    @Override
    public ItemBillAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bill, parent, false);
        return new ItemBillAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemBillAdapter.ItemViewHolder holder, int position) {

        ItemBill currentItem = mItemBillList.get(position);

        int id = currentItem.getId();
        String tinhtrang = currentItem.getTinhtrangHD();

        holder.tvId.setText(Integer.toString(id));
        holder.tvTinhTrangHD.setText(tinhtrang);
        holder.lnCancelBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<ResponseBodyBill> responseBodyBillCall = ApiClient.getProductService().cancelBill(id,
                        "Bearer " + StoreUtil.get(v.getContext(), Contants.requestToken));
                responseBodyBillCall.enqueue(new Callback<ResponseBodyBill>() {
                    @Override
                    public void onResponse(Call<ResponseBodyBill> call, Response<ResponseBodyBill> response) {
                    }

                    @Override
                    public void onFailure(Call<ResponseBodyBill> call, Throwable t) {

                    }
                });
            }
        });

    }


    @Override
    public int getItemCount() {

        return mItemBillList.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tvId;
        private TextView tvTinhTrangHD;
        private LinearLayout lnCancelBill;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id_bill);
            tvTinhTrangHD = itemView.findViewById(R.id.tv_tinhtrangHD);
            lnCancelBill = itemView.findViewById(R.id.ln_cancel_bill);
        }
    }




}


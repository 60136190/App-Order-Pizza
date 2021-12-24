package com.example.oderapp.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oderapp.R;
import com.example.oderapp.activities.ApiClient;
import com.example.oderapp.model.ItemBill;
import com.example.oderapp.model.ItemCart;
import com.example.oderapp.model.Rating;
import com.example.oderapp.model.response.ResponseBodyBill;
import com.example.oderapp.model.response.ResponseBodyCart;
import com.example.oderapp.model.response.ResponseDTO;
import com.example.oderapp.model.response.ResponseRating;
import com.example.oderapp.utils.Contants;
import com.example.oderapp.utils.StoreUtil;

import java.util.HashMap;
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
                        "Bearer " + StoreUtil.get(v.getContext(), Contants.accessToken));
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

        // lấy ra tất cả các bill, sau đó so sánh nếu tình trạng HD là đã nhận hàng thì show dialog để đánh giá.
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Contants.accessToken, "Bearer " + StoreUtil.get(mContext.getApplicationContext(), Contants.accessToken));

        Call<ResponseBodyBill> responseBodyBillCall = ApiClient.getProductService().getAllBill(hashMap);
        responseBodyBillCall.enqueue(new Callback<ResponseBodyBill>() {
            @Override
            public void onResponse(Call<ResponseBodyBill> call, Response<ResponseBodyBill> response) {
                String s = "Đã nhận hàng";
                if (tinhtrang.equals(s)) {
                    holder.lnItemBill.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            final Dialog dialog = new Dialog(v.getContext());
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.dialog_rating);

                            Window window = dialog.getWindow();
                            if (window == null) {
                                return;
                            }

                            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                            WindowManager.LayoutParams windowAtribute = window.getAttributes();
                            window.setAttributes(windowAtribute);


                            EditText edtComment = dialog.findViewById(R.id.edt_comment_dialog);
                            Button btnCancel = dialog.findViewById(R.id.btn_cancel);
                            Button btnSave = dialog.findViewById(R.id.btn_save_address);
                            RatingBar ratingBar = dialog.findViewById(R.id.rating_bar);
                            btnCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();


                            // rating bill
                            btnSave.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int myRating = (int) ratingBar.getRating();
                                    String cmt = edtComment.getText().toString();

                                    Rating rating = new Rating(myRating,cmt);

                                    HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put(Contants.accessToken, "Bearer " + StoreUtil.get(v.getContext(), Contants.accessToken));
                                    hashMap.put(Contants.contentLength, "<calculated when request is sent>");
                                    Call<ResponseRating> loginResponeCall = ApiClient.getService().ratingBill(id, rating,hashMap);
                                    loginResponeCall.enqueue(new Callback<ResponseRating>() {
                                        @Override
                                        public void onResponse(Call<ResponseRating> call, Response<ResponseRating> response) {
                                            Toast.makeText(mContext, "Thanks for your feedback", Toast.LENGTH_SHORT).show();

                                        }

                                        @Override
                                        public void onFailure(Call<ResponseRating> call, Throwable t) {

                                        }
                                    });

                                    dialog.dismiss();
                                }
                            });

                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<ResponseBodyBill> call, Throwable t) {
                t.printStackTrace();
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
        private LinearLayout lnItemBill;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id_bill);
            tvTinhTrangHD = itemView.findViewById(R.id.tv_tinhtrangHD);
            lnCancelBill = itemView.findViewById(R.id.ln_cancel_bill);
            lnItemBill = itemView.findViewById(R.id.ln_item_bill);
        }
    }


}


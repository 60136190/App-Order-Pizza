package com.example.oderapp.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.oderapp.R;
import com.example.oderapp.activities.ApiClient;
import com.example.oderapp.activities.PaymentActivity;
import com.example.oderapp.adapters.ItemCartAdappter;
import com.example.oderapp.model.ItemCart;
import com.example.oderapp.model.response.ResponseBodyCart;
import com.example.oderapp.utils.Contants;
import com.example.oderapp.utils.StoreUtil;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends Fragment {
    private RecyclerView mRecyclerView;



    private TextView tvQuantily;
    private View mview;
    private Button btnCheckOut;
    private ImageView imgDeleteAllItemInCart;
    private SwipeRefreshLayout swipeRefreshLayout;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mview = inflater.inflate(R.layout.fragment_cart, container, false);
        getCart();
        mRecyclerView = mview.findViewById(R.id.rcv_cart);
        btnCheckOut = mview.findViewById(R.id.btn_check_out);
        imgDeleteAllItemInCart = mview.findViewById(R.id.img_delete_all_item_cart);
        swipeRefreshLayout = mview.findViewById(R.id.refresh);

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PaymentActivity.class);
                startActivity(intent);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCart();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },1000);
            }
        });

        imgDeleteAllItemInCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_confirm_delete_all_cart);

                Window window = dialog.getWindow();
                if (window == null) {
                    return;
                }

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAtribute = window.getAttributes();
                window.setAttributes(windowAtribute);


                Button btnCancel = dialog.findViewById(R.id.btn_cancel);
                Button btnDelete = dialog.findViewById(R.id.btn_delete_all_item_in_cart);

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                // show dialog
                dialog.show();
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Call<ResponseBodyCart> responseBodyCartCall = ApiClient.getProductService().deleteAllItemInCart(
                                "Bearer " + StoreUtil.get(v.getContext(), Contants.accessToken));
                        responseBodyCartCall.enqueue(new Callback<ResponseBodyCart>() {
                            @Override
                            public void onResponse(Call<ResponseBodyCart> call, Response<ResponseBodyCart> response) {

                            }

                            @Override
                            public void onFailure(Call<ResponseBodyCart> call, Throwable t) {

                            }
                        });
                        dialog.dismiss();
                    }
                });


            }
        });
        return mview;
    }

    private void getCart() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Contants.accessToken, "Bearer " + StoreUtil.get(getActivity(), Contants.accessToken));

        Call<ResponseBodyCart> responseDTOCall = ApiClient.getProductService().getCart(hashMap);
        responseDTOCall.enqueue(new Callback<ResponseBodyCart>() {
            @Override
            public void onResponse(Call<ResponseBodyCart> call, Response<ResponseBodyCart> response) {
                ItemCartAdappter adappter = new ItemCartAdappter(getContext(), response.body().getData());
                mRecyclerView.setAdapter(adappter);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
                mRecyclerView.addItemDecoration(itemDecoration);
            }

            @Override
            public void onFailure(Call<ResponseBodyCart> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}


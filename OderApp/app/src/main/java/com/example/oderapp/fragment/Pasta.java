package com.example.oderapp.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.oderapp.R;
import com.example.oderapp.adapters.ItemPastaAdappter;
import com.example.oderapp.adapters.ItemPizzaAdappter;
import com.example.oderapp.model.ItemPasta;
import com.example.oderapp.model.ItemPizza;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pasta extends Fragment {
    private RecyclerView mRecyclerView;
    private ItemPastaAdappter mitemPastaAdappter;
    private ArrayList<ItemPasta> mitemPastaList;
    private RequestQueue mRequestQueue;
    public Pasta() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pasta, container, false);
        mRecyclerView = view.findViewById(R.id.rcv_pasta);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        return view;
    }
    private void parseJSON() {
        String url = "http://172.20.10.9:5000/product";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject dt = jsonArray.getJSONObject(i);
                                String pizzaName = dt.getString("tensp");
                                String pizzaImage = dt.getString("url");
                                int pizzaPrice = dt.getInt("gia");
                                String pizzaDetail = dt.getString("chitiet");
                                String pizzaSize = dt.getString("size");
                                mitemPastaList.add(new ItemPasta(pizzaName,pizzaPrice,pizzaImage,pizzaDetail,pizzaSize));
                            }
                            mitemPastaAdappter = new ItemPastaAdappter(getActivity(), mitemPastaList);
                            mRecyclerView.setAdapter(mitemPastaAdappter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        mRequestQueue.add(request);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mitemPastaList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(getContext());
        parseJSON();
    }
}
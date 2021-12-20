package com.example.oderapp.fragmentinfo;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.oderapp.R;
import com.example.oderapp.activities.ApiClient;
import com.example.oderapp.activities.JWTUtils;
import com.example.oderapp.activities.Login;
import com.example.oderapp.activities.LoginRespone;
import com.example.oderapp.activities.SliderActivity;
import com.example.oderapp.adapters.ItemProductAdappter;
import com.example.oderapp.fragment.InfoFragment;
import com.example.oderapp.model.ItemFood;
import com.example.oderapp.model.ResponseBodyDTO;
import com.example.oderapp.model.UserRegister;
import com.example.oderapp.model.request.UserRequest;
import com.example.oderapp.model.response.ResponseDTO;
import com.example.oderapp.utils.Contants;
import com.example.oderapp.utils.StoreUtil;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Account extends AppCompatActivity {

    private EditText edtHoTen;
    private EditText edtUserName;
    private EditText edtNgaySinh;
    private EditText edtDienThoai;

    private Button btnUpdate;
    int male = 0;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initUi();
        getData();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.Male:
                        male = 1;
                        break;
                    case R.id.Female:
                        male = 0;
                        break;
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten = edtHoTen.getText().toString();
                String username = edtUserName.getText().toString();
                String ngaySinh = edtNgaySinh.getText().toString();
                String sdt = edtDienThoai.getText().toString();

                UserRequest userRequest = new UserRequest(hoten, username, ngaySinh, male, sdt);
                updateInfo(userRequest);
            }
        });

    }

    // update
    public void updateInfo(UserRequest userRequest) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Contants.requestToken, "Bearer " + StoreUtil.get(Account.this, Contants.requestToken));
        hashMap.put(Contants.contentLength, "<calculated when request is sent>");
        Call<ResponseDTO> loginResponeCall = ApiClient.getService().updateInfo(hashMap, userRequest);
        loginResponeCall.enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                Toast.makeText(Account.this, "Update Infomation is successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {

            }
        });

    }

    // get data user profile
    public void getData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Contants.requestToken, "Bearer " + StoreUtil.get(Account.this, Contants.accessToken));

        Call<ResponseBodyDTO> loginResponeCall = ApiClient.getService().getProfile(hashMap);
        loginResponeCall.enqueue(new Callback<ResponseBodyDTO>() {
            @Override
            public void onResponse(Call<ResponseBodyDTO> call, Response<ResponseBodyDTO> response) {
                UserRegister userRequest  = response.body().getData().get(0);
                edtHoTen.setText(userRequest.getHoten());
                edtUserName.setText(userRequest.getUsername());
                edtNgaySinh.setText(userRequest.getNgaysinh());
                edtDienThoai.setText(userRequest.getDienthoai());
//                edtGioiTinh.setText(userRequest.getGioitinh());
                // fill more data

            }

            @Override
            public void onFailure(Call<ResponseBodyDTO> call, Throwable t) {

            }
        });
    }

    private void initUi() {
        btnUpdate = findViewById(R.id.buttonUpdate);

        edtHoTen = findViewById(R.id.edt_ho_ten);
        edtUserName = findViewById(R.id.edt_username);
        edtNgaySinh = findViewById(R.id.edt_ngay_sinh);
        edtDienThoai = findViewById(R.id.edt_phone_number);
        radioGroup = findViewById(R.id.radioGroup);
    }
}
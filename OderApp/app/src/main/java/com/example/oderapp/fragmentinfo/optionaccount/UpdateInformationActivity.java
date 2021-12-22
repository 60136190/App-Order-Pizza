package com.example.oderapp.fragmentinfo.optionaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.oderapp.R;
import com.example.oderapp.activities.ApiClient;
import com.example.oderapp.fragmentinfo.Account;
import com.example.oderapp.model.InformationUser;
import com.example.oderapp.model.ResponseBodyDTO;
import com.example.oderapp.model.UserRegister;
import com.example.oderapp.model.request.UserRequest;
import com.example.oderapp.model.response.ResponseDTO;
import com.example.oderapp.model.response.ResponseInformationUser;
import com.example.oderapp.utils.Contants;
import com.example.oderapp.utils.StoreUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateInformationActivity extends AppCompatActivity {
    private EditText edtHoTen;
    private EditText edtUserName;
    private EditText edtNgaySinh;
    private EditText edtDienThoai;
    private EditText edtUrl;
    private ImageView imgBack;
    private Button btnUpdate;
    int male = 0;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_information);
        initUi();
        getData();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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
                String url = edtUrl.getText().toString();

                UserRequest userRequest = new UserRequest(hoten, username, ngaySinh, male, sdt,url);
                updateInfo(userRequest);
            }
        });

    }
    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }

    // update
    public void updateInfo(UserRequest userRequest) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Contants.accessToken, "Bearer " + StoreUtil.get(UpdateInformationActivity.this, Contants.accessToken));
        hashMap.put(Contants.contentLength, "<calculated when request is sent>");
        Call<ResponseDTO> loginResponeCall = ApiClient.getService().updateInfo(hashMap, userRequest);
        loginResponeCall.enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                Toast.makeText(UpdateInformationActivity.this, "Update Infomation is successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {

            }
        });

    }

    // get data user profile
    public void getData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Contants.accessToken, "Bearer " + StoreUtil.get(UpdateInformationActivity.this, Contants.accessToken));

        Call<ResponseInformationUser> loginResponeCall = ApiClient.getService().getProfile(hashMap);
        loginResponeCall.enqueue(new Callback<ResponseInformationUser>() {
            @Override
            public void onResponse(Call<ResponseInformationUser> call, Response<ResponseInformationUser> response) {
                InformationUser informationUser  = response.body().getData().get(0);
                edtHoTen.setText(informationUser.getHoten());
                edtUserName.setText(informationUser.getUsername());
                edtNgaySinh.setText(informationUser.getNgaysinh());
                edtDienThoai.setText(informationUser.getDienthoai());
                edtUrl.setText(informationUser.getUrl());
                // fill more data

            }

            @Override
            public void onFailure(Call<ResponseInformationUser> call, Throwable t) {

            }
        });
    }

    private void initUi() {
        btnUpdate = findViewById(R.id.buttonUpdate);
        edtHoTen = findViewById(R.id.edt_ho_ten);
        edtUserName = findViewById(R.id.edt_username);
        edtNgaySinh = findViewById(R.id.edt_ngay_sinh);
        edtDienThoai = findViewById(R.id.edt_phone_number);
        edtUrl = findViewById(R.id.edt_url);
        radioGroup = findViewById(R.id.radioGroup);
        imgBack = findViewById(R.id.back);
    }
}
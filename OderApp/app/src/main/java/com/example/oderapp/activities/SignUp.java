package com.example.oderapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oderapp.R;
import com.example.oderapp.api.ApiService;
import com.example.oderapp.model.UserRegister;
import com.google.android.material.button.MaterialButton;

import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUp extends AppCompatActivity {

    private TextView tvsignin;
    private MaterialButton btnSignup;

    private EditText edtHoTen;
    private EditText edtUserName;
    private EditText edtNgaySinh;
    private TextView edtGioiTinh;
    private EditText edtDienThoai;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtConfrimPassword;
    private ImageView imgBack;

    private RadioGroup radioGroup;
    int sex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initUi();
//        mListUser = new ArrayList<>();
//        getListUser();
//        Check();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.Male:
                        sex = 1;
                        break;
                    case R.id.Female:
                        sex = 0;
                        break;
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtHoTen.getText().toString()) || TextUtils.isEmpty(edtUserName.getText().toString()) ||
                        TextUtils.isEmpty(edtNgaySinh.getText().toString()) ||
                        TextUtils.isEmpty(edtDienThoai.getText().toString()) || TextUtils.isEmpty(edtEmail.getText().toString()) ||
                        TextUtils.isEmpty(edtPassword.getText().toString()) || TextUtils.isEmpty(edtConfrimPassword.getText().toString())) {
                    String message = "Try again";
                    Toast.makeText(SignUp.this, message, Toast.LENGTH_SHORT).show();
                } else {

                    registerUser();
                }

            }
        });

        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }

    public void initUi() {
        tvsignin = findViewById(R.id.tvSignIn);
        btnSignup = findViewById(R.id.buttonSignUp);

        edtHoTen = findViewById(R.id.edt_ho_ten);
        edtUserName = findViewById(R.id.edt_username);
        edtNgaySinh = findViewById(R.id.edt_ngay_sinh);
        edtGioiTinh = findViewById(R.id.edt_gioi_tinh);
        edtDienThoai = findViewById(R.id.edt_phone_number);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        edtConfrimPassword = findViewById(R.id.edt_confirm_password);
        imgBack = findViewById(R.id.back);
        radioGroup = findViewById(R.id.radioGroup);
    }

    private void registerUser() {

        String hoTen = edtHoTen.getText().toString().trim();
        String userName = edtUserName.getText().toString().trim();
        String ngaySinh = edtNgaySinh.getText().toString().trim();
        String dienThoai = edtDienThoai.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirmPassword = edtConfrimPassword.getText().toString();


        UserRegister userRegister = new UserRegister(hoTen, userName, ngaySinh, sex, dienThoai, email, password);
        ApiService.apiservice.sendPost(userRegister).enqueue(new Callback<UserRegister>() {
            @Override
            public void onResponse(Call<UserRegister> call, Response<UserRegister> response) {
                if (response.isSuccessful()) {
                    String message = "Sign up Successfully";
                    Toast.makeText(SignUp.this, message, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUp.this, Login.class));
                    finish();
                } else {
                    String message = "Try again....";
                    Toast.makeText(SignUp.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserRegister> call, Throwable t) {
                Toast.makeText(SignUp.this, "Call Api Error", Toast.LENGTH_SHORT);
            }
        });
    }

}


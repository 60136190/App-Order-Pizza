package com.example.oderapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.oderapp.R;
import com.example.oderapp.fragmentinfo.optionaccount.ChangePasswordActivity;
import com.example.oderapp.model.request.ForgotPasswordRequest;
import com.example.oderapp.model.response.ResponseForgotPassword;
import com.example.oderapp.utils.Contants;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {

    private EditText edtEmail;
    private Button btnSend;
    private ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initUi();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                ForgotPasswordRequest forgotPassword = new ForgotPasswordRequest(email);
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Contants.contentType, "application/json");
                hashMap.put(Contants.contentLength, "<calculated when request is sent>");
                Call<ResponseForgotPassword> forgotPasswordCall = ApiClient.getService().forgotPassword(hashMap,forgotPassword);

                forgotPasswordCall.enqueue(new Callback<ResponseForgotPassword>() {
                    @Override
                    public void onResponse(Call<ResponseForgotPassword> call, Response<ResponseForgotPassword> response) {

                        Toast.makeText(ForgotPassword.this, "Sent to your email", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseForgotPassword> call, Throwable t) {

                    }
                });
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initUi() {
        edtEmail = findViewById(R.id.inputEmail);
        btnSend = findViewById(R.id.btn_send);
        imgBack = findViewById(R.id.back);
    }
}
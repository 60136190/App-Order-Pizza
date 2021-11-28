package com.example.oderapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oderapp.R;
import com.example.oderapp.api.ApiService;
import com.example.oderapp.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    private Button signin;
    private TextView tvcreate_account;
    private EditText edtUsername;
    private EditText edtPassword;
    private List<User> mListUser;
    private User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUi();
        mListUser = new ArrayList<>();
        getListUser();
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLogin();
            }
        });
        tvcreate_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void initUi(){
        signin = findViewById(R.id.btnSignIn);
        tvcreate_account = findViewById(R.id.tvCreateNewAccount);
        edtUsername = findViewById(R.id.inputEmail);
        edtPassword = findViewById(R.id.inputPassword);
    }

    private void getListUser(){
        ApiService.apiservice.getListUser("meelad")
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        mUser = response.body();
                        Log.e("Info user",mListUser.size()+" ");
                        Toast.makeText(Login.this,"OK",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(Login.this,"Call api erroe",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clickLogin() {
        String strUserName = edtUsername.getText().toString().trim();

        if (mUser == null){
            return;
        }
        boolean isHasUser = false;

            if (strUserName.equals(mUser.getName())){
                isHasUser = true;

        }
        if (isHasUser){
            Intent intent = new Intent(Login.this,MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("object_user",mUser);
            intent.putExtras(bundle);
            startActivity(intent);

        }else{
            Toast.makeText(Login.this,"User name or password invalid",Toast.LENGTH_SHORT).show();
        }
    }

}
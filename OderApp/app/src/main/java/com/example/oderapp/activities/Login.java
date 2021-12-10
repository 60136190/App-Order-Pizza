package com.example.oderapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oderapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    private Button signin;
    private TextView tvcreate_account;

    EditText edtEmail;
    EditText edtPass;

//    private List<User> mListUser;
//    private User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUi();

//        mListUser = new ArrayList<>();
//        getListUser();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtEmail.getText().toString()) || TextUtils.isEmpty(edtPass.getText().toString())){
                    String message = "Try again";
                    Toast.makeText(Login.this,message,Toast.LENGTH_SHORT).show();
                }else{
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setEmail(edtEmail.getText().toString());
                    loginRequest.setPassword(edtPass.getText().toString());
                    loginUser(loginRequest);
                }
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
        edtEmail = findViewById(R.id.inputEmail);
        edtPass = findViewById(R.id.inputPassword);
    }


    public void  loginUser(LoginRequest loginRequest) {
        Call<LoginRespone> loginResponeCall = ApiClient.getService().loginUser(loginRequest);
        loginResponeCall.enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {

                String mess = response.message();
                String a ="Login successful";
                if ((response.isSuccessful())){
                        startActivity(new Intent(Login.this, SliderActivity.class));
                        finish();
                }else{
                    String message = "Email or password is wrong";
                    Toast.makeText(Login.this,message,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginRespone> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(Login.this,message,Toast.LENGTH_SHORT).show();
            }
        });

    }

//    private void getListUser(){
//        ApiService.apiservice.getListUser()
//                .enqueue(new Callback<List<User>>() {
//                    @Override
//                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                        mListUser= response.body();
//                        Log.e("Info user",mListUser.size()+" ");
//                        Toast.makeText(Login.this,"OK",Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<User>> call, Throwable t) {
//                        Toast.makeText(Login.this,"Call api error",Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//    private void clickLogin() {
//        String strEmail = edtEmail.getText().toString().trim();
//        String strPass = edtPass.getText().toString().trim();
//
//
//        if (mListUser == null || mListUser.isEmpty()) {
//            return;
//        }
//        boolean isHasUser = false;
//        for (User user : mListUser) {
//            if (strEmail.equals(user.getName()) && strPass.equals(user.getEmail())) {
//                isHasUser = true;
//                mUser = user;
//                break;
//            }
//
//        }
//        if (isHasUser){
//            Intent i = new Intent(Login.this,HomeFragment.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("object_user",mUser);
//            i.putExtras(bundle);
//
//
//            Intent intent = new Intent(Login.this, SliderActivity.class);
//            startActivity(intent);
//
//        }else{
//            Toast.makeText(Login.this,"User name or password invalid",Toast.LENGTH_SHORT).show();
//        }
//    }


}


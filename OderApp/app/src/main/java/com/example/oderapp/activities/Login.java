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
import androidx.fragment.app.FragmentTransaction;

import com.example.oderapp.R;
import com.example.oderapp.fragment.CartFragment;
import com.example.oderapp.utils.Contants;
import com.example.oderapp.utils.StoreUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    private Button signin;
    private TextView tvcreate_account;

    EditText edtEmail;
    EditText edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUi();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtEmail.getText().toString()) || TextUtils.isEmpty(edtPass.getText().toString())) {
                    String message = "Email or password blank...";
                    Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                } else {
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

    public void initUi() {
        signin = findViewById(R.id.btnSignIn);
        tvcreate_account = findViewById(R.id.tvCreateNewAccount);
        edtEmail = findViewById(R.id.inputEmail);
        edtPass = findViewById(R.id.inputPassword);
    }


    public void loginUser(LoginRequest loginRequest) {
        Call<LoginRespone> loginResponeCall = ApiClient.getService().loginUser(loginRequest);
        loginResponeCall.enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                if (response.body().getStatus() == 200) {
                    StoreUtil.save(Login.this, Contants.accessToken, response.body().getAccessToken());

                }
                int a = 200;
                if ((response.isSuccessful()) && (response.body().getStatus() == a)) {

                    try {
                        JWTUtils.decodeJWT(response.body().getAccessToken());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Intent intentslide = new Intent(Login.this, SliderActivity.class);
                    startActivity(intentslide);
                } else {
                    String message = "Email or password is wrong";
                    Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginRespone> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }

}


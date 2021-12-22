package com.example.oderapp.fragmentinfo;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.oderapp.fragmentinfo.optionaccount.ChangePasswordActivity;
import com.example.oderapp.fragmentinfo.optionaccount.UpdateInformationActivity;
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
    private LinearLayout lnUpdateInformation;
    private LinearLayout lnChangePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initUi();
        lnUpdateInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itUpdate = new Intent(Account.this, UpdateInformationActivity.class);
                startActivity(itUpdate);
            }
        });
        lnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itChangePW = new Intent(Account.this, ChangePasswordActivity.class);
                startActivity(itChangePW);
            }
        });
    }

    private void initUi() {
        lnUpdateInformation = findViewById(R.id.ln_update_information);
        lnChangePassword = findViewById(R.id.ln_change_password);
    }
}
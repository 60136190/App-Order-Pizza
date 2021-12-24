package com.example.oderapp.fragmentinfo.optionaccount;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.oderapp.R;
import com.example.oderapp.activities.ApiClient;
import com.example.oderapp.model.InformationUser;
import com.example.oderapp.model.request.UserRequest;
import com.example.oderapp.model.response.ReponseUrl;
import com.example.oderapp.model.response.ResponseDTO;
import com.example.oderapp.model.response.ResponseInformationUser;
import com.example.oderapp.utils.Contants;
import com.example.oderapp.utils.StoreUtil;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    ImageView imgInfo;
    Button btnImage;
    private static final String TAG = "Upload ###";

    private static int IMAGE_REQ = 1;
    private Uri imagePath;
    Map config = new HashMap();

    int male = 0;
    private RadioGroup radioGroup;
    private RadioButton rdbMale;
    private RadioButton rdbFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_information);
        initUi();
        getData();
//        initCongif();

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

                UserRequest userRequest = new UserRequest(hoten, username, ngaySinh, male, sdt, url);
                updateInfo(userRequest);
            }
        });

    }

    @Override
    public void onBackPressed() {
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
                InformationUser informationUser = response.body().getData().get(0);
                edtHoTen.setText(informationUser.getHoten());
                edtUserName.setText(informationUser.getUsername());
                edtNgaySinh.setText(informationUser.getNgaysinh());
                edtDienThoai.setText(informationUser.getDienthoai());
                edtUrl.setText(informationUser.getUrl());
                if (informationUser.getGioitinh() == 1){
                    rdbMale.setChecked(true);
                }else{
                    rdbFemale.setChecked(true);
                }


//                Glide.with(getApplicationContext())
//                        .load(informationUser.getUrl())
//                        .into(imgInfo);
//
//                imgInfo.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        requestPermission();
//                    }
//                });
            }

            @Override
            public void onFailure(Call<ResponseInformationUser> call, Throwable t) {

            }
        });
    }

//    private void requestPermission() {
//        if (ContextCompat.checkSelfPermission(UpdateInformationActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                == PackageManager.PERMISSION_GRANTED) {
//            selectImage();
//        } else {
//            ActivityCompat.requestPermissions(UpdateInformationActivity.this, new String[]{
//                    Manifest.permission.READ_EXTERNAL_STORAGE
//            }, IMAGE_REQ);
//        }
//
//    }
//
//    private void selectImage() {
//        Intent intent = new Intent();
//        intent.setType("image/*");// if you want to you can use pdf/gif/video
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        someActivityResultLauncher.launch(intent);
//
//    }
//
//    private void initCongif() {
//
//        config.put("cloud_name", "order-food");
//        config.put("api_key", "887187822811327");
//        config.put("api_secret", "weQfCg8Ix_Yo6kjddazodw-1h2A");
////        config.put("secure", true);
//        MediaManager.init(this, config);
//    }
//
//    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        // There are no request codes
//                        Intent data = result.getData();
//                        imagePath = data.getData();
//                        Glide.with(getApplicationContext())
//                                .load(imagePath)
//                                .into(imgInfo);
//
//                        MultipartBody.Part filePart = null;
//                        if (imagePath != null) {
//                            File file = new File(getRealPathFromURI(imagePath));
//                            RequestBody requestBody = RequestBody.create(
//                                    MediaType.parse(getContentResolver().getType(imagePath)),
//                                    file);
//                            filePart =
//                                    MultipartBody.Part.createFormData("file", file.getName(), requestBody);
//                            Log.d("uri", getRealPathFromURI(imagePath));
//                            HashMap<String, String> hashMap = new HashMap<>();
//                            hashMap.put(Contants.accessToken, "Bearer " + StoreUtil.get(UpdateInformationActivity.this, Contants.accessToken));
//                            hashMap.put("Content-Type", "multipart/form-data; boundary=<calculated when request is sent>");
//                            hashMap.put("Content-Length", "<calculated when request is sent>");
//                            Call<ReponseUrl> loginResponeCall = ApiClient.getService().uploadImage(hashMap, filePart);
//                            loginResponeCall.enqueue(new Callback<ReponseUrl>() {
//                                @Override
//                                public void onResponse(Call<ReponseUrl> call, Response<ReponseUrl> response) {
//                                    Log.i(TAG, "onResponse: " + response.body().getPublic_id());
//                                }
//
//                                @Override
//                                public void onFailure(Call<ReponseUrl> call, Throwable t) {
//
//                                }
//                            });
//                        }
//
//
//                    }
//                }
//            });
//
//    private String getRealPathFromURI(Uri contentURI) {
//        String result;
//        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
//        if (cursor == null) {
//            result = contentURI.getPath();
//        } else {
//            cursor.moveToFirst();
//            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//            result = cursor.getString(idx);
//            cursor.close();
//        }
//        return result;
//    }

    private void initUi() {
        btnImage = findViewById(R.id.buttonImage);
        imgInfo = findViewById(R.id.imgUserInfor);
        btnUpdate = findViewById(R.id.buttonUpdate);
        edtHoTen = findViewById(R.id.edt_ho_ten);
        edtUserName = findViewById(R.id.edt_username);
        edtNgaySinh = findViewById(R.id.edt_ngay_sinh);
        edtDienThoai = findViewById(R.id.edt_phone_number);
        edtUrl = findViewById(R.id.edt_url);
        radioGroup = findViewById(R.id.radioGroup);
        rdbMale = findViewById(R.id.Male);
        rdbFemale = findViewById(R.id.Female);

        imgBack = findViewById(R.id.back);
    }
}
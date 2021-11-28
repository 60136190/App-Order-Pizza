package com.example.oderapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.oderapp.R;


public class MessageFragment extends Fragment {
    EditText etSubject,etMessage;
    TextView tvTo;
    Button btsend;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_message, container, false);
        etSubject = view.findViewById(R.id.et_subject);
        etMessage = view.findViewById(R.id.et_message);
        tvTo = view.findViewById(R.id.tv_to);
        btsend = view.findViewById(R.id.bt_send);

        btsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND
                        , Uri.parse("mailto:"+tvTo.getText().toString()));
                intent.putExtra(Intent.EXTRA_EMAIL,tvTo.getText().toString());
                intent.putExtra(Intent.EXTRA_SUBJECT,etSubject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT,etMessage.getText().toString());
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent,"Choose an email client"));
            }
        });
        return view;

    }
}
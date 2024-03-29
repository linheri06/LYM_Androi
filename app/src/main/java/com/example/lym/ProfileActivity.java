package com.example.lym;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    ImageView ivAvatar;

    Button btnListFriend;
    TextView tvOverview;
    TextView tvDangerous;

    Button btnChangeName;
    Button btnChangeBir;
    Button btnChangeEmail;
    Button btnDeleteUser;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ivAvatar = (ImageView) findViewById(R.id.ivAvata_profile);
        btnListFriend = (Button) findViewById(R.id.btnListFriend);
        btnChangeName = (Button) findViewById(R.id.btnChangeName);
        btnChangeBir = (Button) findViewById(R.id.btnChangeBir);
        btnChangeEmail = (Button) findViewById(R.id.btnChangeEmail);
        btnDeleteUser = (Button) findViewById(R.id.btnDeleteUser);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        tvDangerous = (TextView) findViewById(R.id.tvDangerous);
    }
}
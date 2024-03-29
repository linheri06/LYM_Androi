package com.example.lym;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class TakePhotoActivity extends AppCompatActivity {

    ImageButton ibProfileTakePhoto;
    TextView tvListFriendTakePhoto;
    ImageView ivImageTakePhoto;
    ImageButton ibTakePhoto;

    ImageButton ibChangeCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        ibProfileTakePhoto = (ImageButton) findViewById(R.id.ibProfileTakePhoto);
        ibTakePhoto = (ImageButton) findViewById(R.id.ibTakePhoto);
        ibChangeCamera = (ImageButton) findViewById(R.id.ibChangeCamera);
        tvListFriendTakePhoto = (TextView) findViewById(R.id.tvListFriendTakePhoto);
        ivImageTakePhoto = (ImageView) findViewById(R.id.ivImageTakePhoto);
    }
}
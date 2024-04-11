package com.example.lym.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.lym.ImageClassificationAdapter;
import com.example.lym.R;

public class ImageClassification extends AppCompatActivity {
    ImageButton ibtnImage_classify;
    Button btnChat_classify;
    ImageButton btnTakePhoto_classify;
    Spinner spinnerClassify;
    RecyclerView revImage_classify;
    ImageClassificationAdapter imageClassificationAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_classification);

        ibtnImage_classify = (ImageButton) findViewById(R.id.ibtnImage_classify);
        btnTakePhoto_classify = (ImageButton) findViewById(R.id.btnTakePhoto_classify);
        spinnerClassify = (Spinner) findViewById(R.id.spinnerClassify);
        revImage_classify = (RecyclerView) findViewById(R.id.revImage_classify);

        ibtnImage_classify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(ImageClassification.this, ProfileActivity.class);
                startActivity(myintent);

            }
        });
        btnTakePhoto_classify.setOnClickListener(v->{
            Intent intent = new Intent(this, TakePhotoActivity.class);
            startActivity(intent);
        });
        revImage_classify.setLayoutManager(new GridLayoutManager(ImageClassification.this,3));
        revImage_classify.setAdapter(imageClassificationAdapter);
    }
}
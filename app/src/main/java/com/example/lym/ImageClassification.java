package com.example.lym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class ImageClassification extends AppCompatActivity {

    ImageButton ibtnImage_classify;
    Button btnChat_classify;
    ImageButton btnTakePhoto_classify;
    Spinner spinnerClassify;
    RecyclerView revImage_classify;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_classification);

        ibtnImage_classify = (ImageButton) findViewById(R.id.ibtnImage_classify);
        btnChat_classify = (Button) findViewById(R.id.btnChat_classify);
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

    }
}
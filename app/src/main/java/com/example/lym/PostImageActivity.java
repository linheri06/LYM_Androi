package com.example.lym;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;

public class PostImageActivity extends AppCompatActivity {

    ImageView ivImage;

    Button btnPost;

    ImageButton btnSave;
    ImageButton btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_image);

        ivImage = (ImageView) findViewById(R.id.ivImage);
        btnPost = (Button) findViewById(R.id.btnPost);
        btnDelete = (ImageButton) findViewById(R.id.btnDelete);
        btnSave = (ImageButton) findViewById(R.id.btnSave);
    }
}
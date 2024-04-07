package com.example.lym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class PostImageActivity extends AppCompatActivity {

    ImageView ivImage;

    Button btnPost;

    ImageButton btnSave;
    ImageButton btnDelete;

    EditText tvCap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_image);

        ivImage = (ImageView) findViewById(R.id.ivImage);
        btnPost = (Button) findViewById(R.id.btnPost);
        btnDelete = (ImageButton) findViewById(R.id.btnDelete);
        btnSave = (ImageButton) findViewById(R.id.btnSave);
        tvCap = (EditText) findViewById(R.id.edCap);

        byte[] bytes = getIntent().getByteArrayExtra("image");
        File file = (File) getIntent().getExtras().get("file");
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        ivImage.setImageBitmap(bitmap);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear the ImageView
                ivImage.setImageDrawable(null);

                // Delete the file
                if (file.exists()) {
                    if (file.delete()) {
                        Toast.makeText(PostImageActivity.this, "Image and file deleted!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(PostImageActivity.this, "Failed to delete file!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PostImageActivity.this, "File does not exist.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the bitmap from the ImageView
                Bitmap bitmap = ((BitmapDrawable) ivImage.getDrawable()).getBitmap();

                // Construct a MediaStore content values object with image metadata
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "Image From My App");
                values.put(MediaStore.Images.Media.DESCRIPTION, "Description of the image");
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/png"); // Assuming PNG format

                // Insert the image into the MediaStore
                Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                try (OutputStream out = getContentResolver().openOutputStream(uri)) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // Adjust compression as needed
                    Toast.makeText(PostImageActivity.this, "Image saved to gallery!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(PostImageActivity.this, "Failed to save image!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });



    }
}
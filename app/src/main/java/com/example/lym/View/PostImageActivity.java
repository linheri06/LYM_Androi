package com.example.lym.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.example.lym.Model.Image;
import com.example.lym.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

public class PostImageActivity extends AppCompatActivity {

    ImageView ivImage;

    Button btnPost;

    ImageButton btnSave;
    ImageButton btnDelete;

    EditText edCap;
    FirebaseAuth mAuthencation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_image);

        mAuthencation = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuthencation.getCurrentUser();

        ivImage = (ImageView) findViewById(R.id.ivImage);
        btnPost = (Button) findViewById(R.id.btnPost);
        btnDelete = (ImageButton) findViewById(R.id.btnDelete);
        btnSave = (ImageButton) findViewById(R.id.btnSave);
        edCap = (EditText) findViewById(R.id.edCap);


        byte[] bytes = getIntent().getByteArrayExtra("image");
        File file = (File) getIntent().getExtras().get("file");
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        Bitmap rotatedBitmap = rotateBitmap(bitmap);

        ivImage.setImageBitmap(rotatedBitmap);
        //ivImage.setImageBitmap(bitmap);
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://lymandroi.appspot.com");
        StorageReference storageRef = storage.getReference();
        Calendar calendar = Calendar.getInstance();
        StorageReference mountainsRef = storageRef.child("image"+calendar.getTimeInMillis()+"png");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear the ImageView
                ivImage.setImageDrawable(null);

                // Delete the file
                if (file.exists()) {
                    if (file.delete()) {
                        Toast.makeText(PostImageActivity.this, "Image and file deleted!", Toast.LENGTH_SHORT).show();
                        Intent myintent = new Intent(PostImageActivity.this, TakePhotoActivity.class);
                        startActivity(myintent);
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

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the data from an ImageView as bytes
                ivImage.setDrawingCacheEnabled(true);
                ivImage.buildDrawingCache();
                Log.d("UserCount","aaaa3");
                Bitmap bitmap = ((BitmapDrawable) ivImage.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] data = baos.toByteArray();

                // ... mã tải lên của bạn (ví dụ: putBytes)

                mountainsRef.putBytes(data)
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Xử lý tải lên không thành công
                                Toast.makeText(PostImageActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // Lấy URL tải xuống sau khi tải lên thành công
                                //Toast.makeText(PostImageActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                                taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String imageUrl = uri.toString();
                                        Log.d("UserCount", imageUrl);
                                        DatabaseReference imageData = FirebaseDatabase.getInstance().getReference("images");
                                        long time = System.currentTimeMillis();
                                        Image image = new Image(imageUrl,edCap.getText().toString(),time);

                                        imageData.child(firebaseUser.getUid()).child(String.valueOf(time)).setValue(image).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Toast.makeText(PostImageActivity.this, "successfully", Toast.LENGTH_LONG).show();
                                                    Intent intent = new Intent(PostImageActivity.this,TakePhotoActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }else {
                                                    Toast.makeText(PostImageActivity.this, "User registered failed", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        });
            }
        });
    }

    private Bitmap rotateBitmap(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.setRotate(90);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
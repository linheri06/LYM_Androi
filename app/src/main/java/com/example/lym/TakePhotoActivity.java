package com.example.lym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
//import androidx.core.executor.NiceExecutors;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView; // Replace SurfaceView with TextureView
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.util.concurrent.ExecutionException;

import androidx.camera.core.CameraX;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureConfig;
import androidx.camera.core.PreviewConfig;
import androidx.camera.core.UseCaseConfiguration;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView; // Replace SurfaceView with PreviewView

public class TakePhotoActivity extends AppCompatActivity {

    private static final String TAG = "TakePhotoActivity";

    private boolean isFrontFacing = false;  // Flag to track camera orientation

    // ImageButton references (as before)
    private ImageButton ibProfileTakePhoto;
    private TextView tvListFriendTakePhoto;
    private ImageView ivImageTakePhoto;
    private ImageButton ibTakePhoto;
    private ImageButton ibChangeCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        // Initialize view references (as before)
        ibProfileTakePhoto = (ImageButton) findViewById(R.id.ibProfileTakePhoto);
        ibTakePhoto = (ImageButton) findViewById(R.id.ibTakePhoto);
        ibChangeCamera = (ImageButton) findViewById(R.id.ibChangeCamera);
        tvListFriendTakePhoto = (TextView) findViewById(R.id.tvListFriendTakePhoto);

    }


}



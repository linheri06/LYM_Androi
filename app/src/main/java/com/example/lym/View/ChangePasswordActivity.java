package com.example.lym.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;

import com.example.lym.R;

public class ChangePasswordActivity extends AppCompatActivity {

    Button btnChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        btnChangePassword = (Button) findViewById(R.id.btnChangePassword);
    }
}
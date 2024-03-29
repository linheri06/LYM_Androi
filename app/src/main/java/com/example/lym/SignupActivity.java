package com.example.lym;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;

public class SignupActivity extends AppCompatActivity {

    TextView tSignup;

    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        tSignup = (TextView) findViewById(R.id.tSignup);
        btnSignup = (Button) findViewById(R.id.btnSignup);
    }
}
package com.example.lym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextView tLogin;

    Button btnLogin;

    Button btnSignup;

    TextView tVForgotPw;

    EditText eTUsername;

    EditText eTPW;


    private void Login(){
        String Username = eTUsername.getText().toString();
        String PW = eTPW.getText().toString();
        FirebaseAuth mAuthencation = null;
        mAuthencation.signInWithEmailAndPassword(Username,PW)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(LoginActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @SuppressLint("MissingInflatedId")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tLogin = (TextView) findViewById(R.id.tLogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        tVForgotPw = (TextView) findViewById(R.id.tVForgotPw);
        eTUsername = (EditText) findViewById(R.id.eTUsername);
        eTPW = (EditText) findViewById(R.id.eTPW);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(LoginActivity.this, TakePhotoActivity.class);
                startActivity(myintent);

            }
        });


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(myintent);

            }
        });

        tVForgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(LoginActivity.this, ChangePasswordActivity.class);
                startActivity(myintent);

            }
        });

    }
}
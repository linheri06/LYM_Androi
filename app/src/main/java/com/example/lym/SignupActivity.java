package com.example.lym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.BreakIterator;

public class SignupActivity extends AppCompatActivity {

    TextView tSignup;

    Button btnSignup;

    EditText eTEmail;

    EditText eTPassword;

    FirebaseAuth mAuthencation;




    private void Signup(){

        String email = eTEmail.getText().toString();
        String password = eTPassword.getText().toString();
        mAuthencation.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(SignupActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuthencation = FirebaseAuth.getInstance();

        tSignup = (TextView) findViewById(R.id.tSignup);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        eTEmail = (EditText) findViewById(R.id.eTEmail);
        eTPassword = (EditText) findViewById(R.id.eTPassword);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signup();
            }
        });

    }
}

package com.example.lym;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.example.lym.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;
import java.util.concurrent.atomic.AtomicInteger;

public class SignupActivity extends AppCompatActivity {

    TextView tSignup;

    Button btnSignup;

    EditText eTEmail;

    EditText eTPassword;

    FirebaseAuth mAuthencation;




    private void Signup(String email, String password){
        mAuthencation.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            FirebaseUser firebaseUser = mAuthencation.getCurrentUser();
                            //add realtime
                            User user = new User(email, "LYM"+firebaseUser.getUid(), "https://firebasestorage.googleapis.com/v0/b/lymandroi.appspot.com/o/lym.png?alt=media&token=7024cb32-a8eb-4a97-81ad-3a23ba9d9c28","12/04/2024");
                            DatabaseReference userData = FirebaseDatabase.getInstance().getReference("users");
                            DatabaseReference imageData = FirebaseDatabase.getInstance().getReference("images");
                            DatabaseReference friendData = FirebaseDatabase.getInstance().getReference("friends");
                            imageData.child(firebaseUser.getUid()).setValue("");
                            friendData.child(firebaseUser.getUid()).setValue("");
                            userData.child(firebaseUser.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        //send verification email
                                        firebaseUser.sendEmailVerification();
                                        Toast.makeText(SignupActivity.this, "User registered successfully", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Toast.makeText(SignupActivity.this, "User registered failed", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
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
                String email, password;
                email = String.valueOf(eTEmail.getText());
                password = String.valueOf(eTPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignupActivity.this, "Enter email", Toast.LENGTH_LONG).show();
                    eTEmail.setError("Email is required");
                    eTEmail.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignupActivity.this, "Enter password", Toast.LENGTH_LONG).show();
                    eTPassword.setError("Password is required");
                    eTPassword.requestFocus();
                }else {
                    Signup(email,password);
                }
            }
        });
    }

}

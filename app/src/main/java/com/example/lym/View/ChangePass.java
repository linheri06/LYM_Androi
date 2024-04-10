package com.example.lym.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lym.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangePass extends AppCompatActivity {

    TextView tvChangePass;
    TextView tvPassPresent;
    TextView tvNewPass;
    EditText edPassPresent;
    EditText edNewPass;
    EditText edReNewPass;
    Button btnSavePass;

    FirebaseAuth mAuthencation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        mAuthencation = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuthencation.getCurrentUser();

        tvChangePass = (TextView) findViewById(R.id.tvChangePass);
        tvPassPresent = (TextView) findViewById(R.id.tvPassPresent);
        tvNewPass = (TextView) findViewById(R.id.tvNewPass);
        edPassPresent = (EditText) findViewById(R.id.edPassPresent);
        edNewPass = (EditText) findViewById(R.id.edNewPass);
        edReNewPass = (EditText) findViewById(R.id.edReNewPass);
        btnSavePass = (Button) findViewById(R.id.btnSavePass);

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.getUid());
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        btnSavePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String re_pass, pass, pre_pass;
                re_pass = String.valueOf(edReNewPass.getText());
                pass = String.valueOf(edNewPass.getText());
                pre_pass = String.valueOf(edPassPresent.getText());
                if(TextUtils.isEmpty(pre_pass)){
                    edPassPresent.setError("Nhập mật khẩu cũ");
                    edPassPresent.requestFocus();
                } else if (TextUtils.isEmpty(pass)) {
                    edNewPass.setError("Nhập mật kẩu mới");
                    edNewPass.requestFocus();
                }else if (TextUtils.isEmpty(re_pass)) {
                    edReNewPass.setError("Nhập lại mật kẩu mới");
                    edReNewPass.requestFocus();
                }else if (!pass.equals(re_pass)) {
                    edReNewPass.setError("Nhập không khớp mật kẩu mới");
                    edReNewPass.requestFocus();
                } else {
                    AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), pre_pass);
                    user.reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Log.d("UserCount", "User re-authenticated.");
                                    showConfirmationDialog(usersRef, firebaseUser);
                                }
                            });
                }

            }
        });
    }
    private void showConfirmationDialog(DatabaseReference usersRef,  FirebaseUser firebaseUser) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận đổi Pass");
        builder.setMessage("Bạn có chắc chắn muốn đổi mật khẩu không?");
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateUserPass(usersRef,firebaseUser);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void updateUserPass(DatabaseReference usersRef,  FirebaseUser firebaseUser) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String newPass = edNewPass.getText().toString();
        user.updatePassword(newPass)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ChangePass.this, "Bạn đã đổi mật khẩu thành công!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ChangePass.this,ProfileActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(ChangePass.this, "Lỗi tài khoản!!!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
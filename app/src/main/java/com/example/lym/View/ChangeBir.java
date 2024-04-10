package com.example.lym.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lym.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangeBir extends AppCompatActivity {

    TextView tvChangeBir;
    TextView tvBirPresent;
    TextView tvNewBir;
    EditText edBirPresent;
    EditText edNewBir;
    Button btnSaveBir;
    FirebaseAuth mAuthencation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_bir);

        mAuthencation = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuthencation.getCurrentUser();

        tvChangeBir = (TextView) findViewById(R.id.tvChangeBir);
        tvBirPresent = (TextView) findViewById(R.id.tvBirPresent);
        tvNewBir = (TextView) findViewById(R.id.tvNewBir);
        edBirPresent = (EditText) findViewById(R.id.edBirPresent);
        edNewBir = (EditText) findViewById(R.id.edNewBir);
        btnSaveBir = (Button) findViewById(R.id.btnSaveBir);

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.getUid());
        DatabaseReference Bir = usersRef.child("birthdate");
        Bir.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Bir = snapshot.getValue(String.class);
                edBirPresent.setText(Bir);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý khi có lỗi xảy ra
            }
        });

        btnSaveBir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String date;
                date = String.valueOf(edNewBir.getText());
                if(TextUtils.isEmpty(date)){
                    edNewBir.setError("Nhập ngày sinh của bạn");
                    edNewBir.requestFocus();
                }else {
                    showConfirmationDialog(usersRef);
                }
            }
        });
    }
    private void showConfirmationDialog(DatabaseReference usersRef) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận đổi sinh nhật");
        builder.setMessage("Bạn có chắc chắn muốn đổi sinh nhật thành " + edNewBir.getText().toString() + "?");
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateUserBir(usersRef);
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


    private void updateUserBir(DatabaseReference usersRef) {
        String newBir = edNewBir.getText().toString();
        usersRef.child("birthdate").setValue(newBir)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ChangeBir.this, "Bạn đã đổi sinh nhật thành công!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ChangeBir.this,ProfileActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(ChangeBir.this, "Lỗi!!!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }



}
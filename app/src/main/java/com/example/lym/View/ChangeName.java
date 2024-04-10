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
import com.squareup.picasso.Picasso;

public class ChangeName extends AppCompatActivity {

    TextView tvChangeName;
    TextView tvNamePresent;
    TextView tvNewName;
    EditText edNamePresent;
    EditText edNewName;
    Button btnSaveName;
    FirebaseAuth mAuthencation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);

        mAuthencation = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuthencation.getCurrentUser();

        tvChangeName = (TextView) findViewById(R.id.tvChangeName);
        tvNamePresent = (TextView) findViewById(R.id.tvNamePresent);
        tvNewName = (TextView) findViewById(R.id.tvNewName);
        edNamePresent = (EditText) findViewById(R.id.edNamePresent);
        edNewName = (EditText) findViewById(R.id.edNewName);
        btnSaveName = (Button) findViewById(R.id.btnSaveName);

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.getUid());
        DatabaseReference name = usersRef.child("name");
        name.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.getValue(String.class);
                edNamePresent.setText(name);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý khi có lỗi xảy ra
            }
        });

        btnSaveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name;
                name = String.valueOf(edNewName.getText());
                if(TextUtils.isEmpty(name)){
                    edNewName.setError("Nhập tên mới của bạn");
                    edNewName.requestFocus();
                }else {
                    showConfirmationDialog(usersRef);
                }
            }
        });
    }

    private void showConfirmationDialog(DatabaseReference usersRef) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận đổi tên"); // Title in Vietnamese: Confirm Name Change
        builder.setMessage("Bạn có chắc chắn muốn đổi tên thành " + edNewName.getText().toString() + "?"); // Message in Vietnamese: Are you sure you want to change your name to ...?
        builder.setPositiveButton("Đổi tên", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Update name on confirmation
                updateUserName(usersRef);
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


    private void updateUserName(DatabaseReference usersRef) {
        String newName = edNewName.getText().toString();
        usersRef.child("name").setValue(newName)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ChangeName.this, "Bạn đã đổi tên thành công!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ChangeName.this,ProfileActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(ChangeName.this, "Lỗi!!!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
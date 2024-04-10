
package com.example.lym.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.lym.R;

public class WatchPostActivity extends AppCompatActivity {

    ImageButton iBAvatar;

    ImageButton iBMessage;

    ImageView iVPost;

    TextView tVNote;

    ImageView iVAvatar;

    Button btnTakePhoto;

    Button btnImageClassification;

    Button btnSave;

    Button btnDelete;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_post);

        iBAvatar = (ImageButton) findViewById(R.id.iBAvatar);
        iBMessage = (ImageButton) findViewById(R.id.iBmessage);
        iVPost = (ImageView) findViewById(R.id.iVPost);
        tVNote = (TextView) findViewById(R.id.tVNote);
        iVAvatar = (ImageView) findViewById(R.id.iVAvatar);
        btnTakePhoto = (Button) findViewById(R.id.btnTakePhoto);
        btnImageClassification = (Button) findViewById(R.id.btnImageClassification);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        iBAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(WatchPostActivity.this, ProfileActivity.class);
                startActivity(myintent);

            }


        });

        btnImageClassification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(WatchPostActivity.this, ProfileActivity.class);
                startActivity(myintent);

            }
        });


    }
}
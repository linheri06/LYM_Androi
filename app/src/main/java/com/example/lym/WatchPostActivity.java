
package com.example.lym;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;

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
    }
}
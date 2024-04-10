package com.example.lym.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.*;

import com.example.lym.R;

public class AddDeleteFriend extends AppCompatActivity {

    Button btnComeBack;
    Button btnSearch;
    TextView tvNumberFried;
    EditText edtSearch;
    TextView tvFriedList;
    RecyclerView revFriendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delete_friend);

        btnComeBack = (Button) findViewById(R.id.btnComeBack);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        tvNumberFried = (TextView) findViewById(R.id.tvNumberFried);
        edtSearch = (EditText) findViewById(R.id.edtSearch);
        tvFriedList = (TextView) findViewById(R.id.tvFriedList);
        revFriendList = findViewById(R.id.revFriendList);

    }
}
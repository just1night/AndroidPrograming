package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class NovelInfor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_infor);

        TextView inf = findViewById(R.id.testv);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        if (bundle != null) {
            String valueShow = bundle.getString( "name");

            Toast.makeText(this, "Show value: " + valueShow, Toast.LENGTH_SHORT).show();

            inf.setText(valueShow);
        }
    }
}
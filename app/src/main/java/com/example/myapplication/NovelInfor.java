package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class NovelInfor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_infor);

        ImageView imageView = findViewById(R.id.imginfor);
        TextView title = findViewById(R.id.title_name);
        TextView info  = findViewById(R.id.name_infor);
        TextView aut = findViewById(R.id.authortxt);
        TextView discrp = findViewById(R.id.discriptxt);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        if (bundle != null) {
            String valueShowname = bundle.getString( "name");
            String valueShowimg = bundle.getString("img");
            String valueShowauthor = bundle.getString("aut");
            String valueShowdiscrip = bundle.getString("discrip");
            title.setText(valueShowname);
            info.setText(valueShowname);
            Glide.with(this).load(valueShowimg).into(imageView);
            aut.setText("Tác Giả: "+valueShowauthor);
            discrp.setText(valueShowdiscrip);
        }
        TextView goback = findViewById(R.id.go_back);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NovelInfor.this, HomeAct.class);
                startActivity(intent);
            }
        });

//       đánh giá truyện
        RatingBar vote = findViewById(R.id.ratingBar);
        float result = vote.getRating();
        Button danhgia = findViewById(R.id.btndanhgia);
        danhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//       comment
        EditText comment = findViewById(R.id.editTextComment);
        Button submit = findViewById(R.id.btnsubmitmess);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });








    }
}
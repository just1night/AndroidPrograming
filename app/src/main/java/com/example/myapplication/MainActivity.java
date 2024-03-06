package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText acc = findViewById(R.id.editTextTextAcc);
        EditText pass = findViewById(R.id.editTextTextPassword);

        Button reg1 = findViewById(R.id.buttonResigter1);
        Button log = findViewById(R.id.buttonLogin1);
        reg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(MainActivity.this,RegisterAct.class);
                startActivity(i);
            }
        });
        log.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent i = new Intent();
                i .setClass(MainActivity.this, HomeAct.class);
                startActivity(i);
            }
        });


    }
    @Override
    protected void onPause() {
        super.onPause();}
}
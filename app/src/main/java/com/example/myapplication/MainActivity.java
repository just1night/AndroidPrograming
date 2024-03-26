package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.api.ApiService;
import com.example.myapplication.api.LoginResponse;
import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.novel.Novel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                LoginUser(acc.getText().toString(),pass.getText().toString());
            }
        });


    }

    private void LoginUser(String username, String password) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        Call<LoginResponse> call = apiService.loginUser(username, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {


                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.isSuccess()) {
                        // Đăng nhập thành công, chuyển đến màn hình chính hoặc thực hiện các hành động khác
                        Toast.makeText(MainActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        Intent i = new Intent();
                        i .setClass(MainActivity.this, HomeAct.class);
                        startActivity(i);
                    } else {
                        // Đăng nhập không thành công, thông báo lỗi
                        Toast.makeText(MainActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Xử lý khi có lỗi trên máy chủ
                    Toast.makeText(MainActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Xử lý khi có lỗi kết nối
                Toast.makeText(MainActivity.this,  t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    
    @Override
    protected void onPause() {
        super.onPause();}



}
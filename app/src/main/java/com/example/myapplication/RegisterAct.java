package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        EditText email = findViewById(R.id.editTextTextEmail);
        EditText acc = findViewById(R.id.editTextTextAcc1);
        EditText pass = findViewById(R.id.editTextTextPassword1);
        EditText repass = findViewById(R.id.editTextTextRePassword);

        Button regis = findViewById(R.id.buttonResigter2);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pass.getText().toString().equals(repass.getText().toString())){
                    RegisterUser(acc.getText().toString(),pass.getText().toString(),email.getText().toString());
                }else{
                    Toast.makeText(RegisterAct.this, "hãy kiểm tra lại mật khẩu", Toast.LENGTH_SHORT).show();
                };


            }
        });

    }
    private void RegisterUser(String username, String password,String email) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        Call<RegisterResponse> call = apiService.RegisterUser(username, password,email);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {


                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    if (registerResponse.isSuccess()) {
                        // Đăng nhập thành công, chuyển đến màn hình chính hoặc thực hiện các hành động khác
                        Toast.makeText(RegisterAct.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent();
                        i.setClass(RegisterAct.this,MainActivity.class);
                        startActivity(i);
                    } else {
                        // Đăng nhập không thành công, thông báo lỗi
                        Toast.makeText(RegisterAct.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Xử lý khi có lỗi trên máy chủ
                    Toast.makeText(RegisterAct.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                // Xử lý khi có lỗi kết nối
                Toast.makeText(RegisterAct.this,  t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
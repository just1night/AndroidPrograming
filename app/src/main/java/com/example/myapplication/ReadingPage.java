package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.reading.IClickreading;
import com.example.myapplication.reading.Reading;
import com.example.myapplication.reading.ReadingAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadingPage extends AppCompatActivity {
    private ReadingAdapter readingAdapter;
    private RecyclerView rcvReading;

    private ArrayList<Reading> readings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_page);

        Intent intent1 = getIntent();
        Bundle bundle = intent1.getExtras();

        int id = bundle.getInt("key_id");

        TextView txt = findViewById(R.id.tvtitlechapter);
        txt.setText(Integer.toString(id));


        rcvReading = findViewById(R.id.rcvreading);
        readingAdapter = new ReadingAdapter(this);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvReading.setLayoutManager(linearLayoutManager);
        getReadingPagelist(id);




    }


    private void getReadingPagelist(int idchapter) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<ArrayList<Reading>> call = apiService.getReadingPage(idchapter);
        call.enqueue(new Callback<ArrayList<Reading>>() {
            @Override
            public void onResponse(Call<ArrayList<Reading>> call, Response<ArrayList<Reading>> response) {
                if (response.isSuccessful()) {
                    readings = response.body();
                    readingAdapter.setReadings(readings);
                    // Chỉ thiết lập adapter sau khi nhận được dữ liệu thành công
                    rcvReading.setAdapter(readingAdapter);
                } else {
                    Toast.makeText(ReadingPage.this, "không lấy được dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Reading>> call, Throwable t) {
                Toast.makeText(ReadingPage.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.api.ApiService;
import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.reading.IClickreading;
import com.example.myapplication.reading.Reading;
import com.example.myapplication.reading.ReadingAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class ReadingPage extends AppCompatActivity {

    private RecyclerView rcvReading;
    private ReadingAdapter readingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_page);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        int id = bundle.getInt("idchaper");




        rcvReading = findViewById(R.id.rcvreading);
        readingAdapter = new ReadingAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvReading.setLayoutManager(linearLayoutManager);
        getReadingPagelist(id);


    }

    private void getReadingPagelist(int id) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<ArrayList<Reading>> call = apiService.getReadingPage(id);

    }

    private List<Reading> getContent() {
        List<Reading> readings = new ArrayList<>();
        readings.add(new Reading(1, 1, "https://i.imgur.com/1.jpg", "Content 1"));
        readings.add(new Reading(2, 1, "https://i.imgur.com/2.jpg", "Content 2"));
        readings.add(new Reading(3, 1, "https://i.imgur.com/3.jpg", "Content 3"));
        readings.add(new Reading(4, 1, "https://i.imgur.com/4.jpg", "Content 4"));
        readings.add(new Reading(5, 1, "https://i.imgur.com/5.jpg", "Content 5"));
        readings.add(new Reading(6, 1, "https://i.imgur.com/6.jpg", "Content 6"));
        readings.add(new Reading(7, 1, "https://i.imgur.com/7.jpg", "Content 7"));
        readings.add(new Reading(8, 1, "https://i.imgur.com/8.jpg", "Content 8"));
        readings.add(new Reading(9, 1, "https://i.imgur.com/9.jpg", "Content 9"));
        readings.add(new Reading(10, 1, "https://i.imgur.com/10.jpg", "Content 10"));
        return readings;
    }
}
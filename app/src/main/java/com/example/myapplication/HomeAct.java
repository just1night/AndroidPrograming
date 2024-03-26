package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.category.Category;
import com.example.myapplication.category.CategoryAdapter;
import com.example.myapplication.novel.Novel;
import com.example.myapplication.novel.NovelAdapter;

import java.security.PrivateKey;
import java.util.ArrayList;

public class HomeAct extends AppCompatActivity {
    private RecyclerView rv;
    private CategoryAdapter categoryadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rv = findViewById(R.id.rcv_category);
        categoryadapter = new CategoryAdapter(this);

        LinearLayoutManager linearlayoutmanager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rv.setLayoutManager(linearlayoutmanager);

        categoryadapter.setData(getListCatagory());
        rv.setAdapter(categoryadapter);





    }

    private ArrayList<Category> getListCatagory(){
        ArrayList<Category> lst = new ArrayList<>();
        ArrayList<Novel> lstnovel = new ArrayList<>();
        lstnovel.add(new Novel("novel 1","https://gamek.mediacdn.vn/133514250583805952/2022/2/17/buy4-16450769457662019240304.jpg"));
        lstnovel.add(new Novel("novel 2","https://a.storyblok.com/f/178900/737x1200/2c3f2808d5/attack-on-titan.jpg/m/filters:quality(95)format(webp)"));

        lst.add(new Category("Truyện đang nổi",lstnovel));


        return lst;
    }



}
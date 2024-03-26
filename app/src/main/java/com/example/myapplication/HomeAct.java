package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.api.ApiService;
import com.example.myapplication.api.LoginResponse;
import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.category.Category;
import com.example.myapplication.category.CategoryAdapter;
import com.example.myapplication.novel.Novel;
import com.example.myapplication.novel.NovelAdapter;

import java.security.PrivateKey;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

public class HomeAct extends AppCompatActivity {
    private RecyclerView rv;
    private CategoryAdapter categoryadapter;

    private ArrayList<Novel> lstnv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rv = findViewById(R.id.rcv_category);
        categoryadapter = new CategoryAdapter(this);

        LinearLayoutManager linearlayoutmanager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rv.setLayoutManager(linearlayoutmanager);

        lstnv = new ArrayList<>();
        Callgettop5();


    }

    private ArrayList<Category> getListCatagory(){
        ArrayList<Category> lst = new ArrayList<>();


        ArrayList<Novel> lstnovel = new ArrayList<>();
        lstnovel.add(new Novel("novel 1","https://gamek.mediacdn.vn/133514250583805952/2022/2/17/buy4-16450769457662019240304.jpg"));
        lstnovel.add(new Novel("novel 2","https://a.storyblok.com/f/178900/737x1200/2c3f2808d5/attack-on-titan.jpg/m/filters:quality(95)format(webp)"));

        lst.add(new Category("Truyện đang nổi",lstnovel));
        return lst;
    }
    private void Callgettop5(){
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);


        Call<ArrayList<Novel>> call = apiService.Getlist5("");
        call.enqueue(new Callback<ArrayList<Novel>>() {
            @Override
            public void onResponse(Call<ArrayList<Novel>> call, Response<ArrayList<Novel>> response) {
                if (response.isSuccessful()) {
                    // Phản hồi từ máy chủ thành công
                    lstnv = response.body();
                    ArrayList<Category> lst = new ArrayList<>();
                    // Sử dụng dữ liệu ở đây
                    // Ví dụ: categoryadapter.setData(lstnv);
                    lst.add(new Category("Truyện đang nổi",lstnv));
                    categoryadapter.setData(lst);
                    rv.setAdapter(categoryadapter);
                } else {
                    // Xử lý khi có phản hồi không thành công
                    // Ví dụ: Hiển thị thông báo lỗi
                    Toast.makeText(HomeAct.this, "Không thể nhận dữ liệu từ máy chủ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Novel>> call, Throwable t) {
// Xử lý khi có lỗi kết nối
                Toast.makeText(HomeAct.this,  t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
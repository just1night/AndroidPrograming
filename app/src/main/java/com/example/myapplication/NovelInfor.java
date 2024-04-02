package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.myapplication.api.ApiService;
import com.example.myapplication.api.CommentResponse;
import com.example.myapplication.api.LoginResponse;
import com.example.myapplication.api.RatingResponse;
import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.api.VoteResponse;
import com.example.myapplication.chapter.Chapter;
import com.example.myapplication.chapter.ChapterAdapter;
import com.example.myapplication.classobject.Account;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NovelInfor extends AppCompatActivity {
    private RecyclerView rcvChapter;
    private ChapterAdapter chapterAdapter;


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
        int valueShowidnovel = bundle.getInt("idnovel");
        String valueShowname = bundle.getString( "name");
        String valueShowimg = bundle.getString("img");
        String valueShowauthor = bundle.getString("aut");
        String valueShowdiscrip = bundle.getString("discrip");
        title.setText(valueShowname);
        info.setText(valueShowname);
        Glide.with(this).load(valueShowimg).into(imageView);
        aut.setText("Tác Giả: "+valueShowauthor);
        discrp.setText(valueShowdiscrip);

        TextView goback = findViewById(R.id.go_back);

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //lấy đánh giá
        getVoteofNovel(valueShowidnovel);

//       đánh giá truyện
        Account account = Account.getInstance();
        int idacc =  account.getId();

        RatingBar vote = findViewById(R.id.ratingBar);

        TextView rate = findViewById(R.id.ratingtxt);

        Button danhgia = findViewById(R.id.btndanhgia);
        danhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float result = vote.getRating();
                takeVote(idacc,valueShowidnovel,result);
            }
        });

//       comment
        EditText comment = findViewById(R.id.editTextComment);
        String content = comment.getText().toString();
        Button submit = findViewById(R.id.btnsubmitmess);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    sendComment(valueShowidnovel,idacc,content);
            }
        });

    //Recycle view chapter
        rcvChapter = findViewById(R.id.rcvChapter);
        chapterAdapter = new ChapterAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvChapter.setLayoutManager(linearLayoutManager);
        getListChapter(valueShowidnovel);
        //chapterAdapter.setChapters(getListChapter(valueShowidnovel));
        rcvChapter.setAdapter(chapterAdapter);
    }

    private void getListChapter(int idNovel) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<ArrayList<Chapter>> call = apiService.getChapter(idNovel);
        call.enqueue(new Callback<ArrayList<Chapter>>() {
            @Override
            public void onResponse(Call<ArrayList<Chapter>> call, Response<ArrayList<Chapter>> response) {
                if(response.isSuccessful()){
                    ArrayList chapters = response.body();
                    chapterAdapter.setChapters(chapters);
                }else {
                    Toast.makeText(NovelInfor.this, "không lấy được dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Chapter>> call, Throwable t) {
                Toast.makeText(NovelInfor.this,  t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void takeVote(int idacc,int idnovel,float rating){
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<VoteResponse> call = apiService.VoteResp(idacc, idnovel, rating);
        call.enqueue(new Callback<VoteResponse>() {
            @Override
            public void onResponse(Call<VoteResponse> call, Response<VoteResponse> response) {
                VoteResponse voteResponse = response.body();
                if (response.isSuccessful()) {
                    if (voteResponse.isSuccess()) {
                        Toast.makeText(NovelInfor.this, voteResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(NovelInfor.this, voteResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Xử lý khi có lỗi trên máy chủ
                    Toast.makeText(NovelInfor.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<VoteResponse> call, Throwable t) {
                Toast.makeText(NovelInfor.this,  t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void getVoteofNovel(int idnovel){
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<RatingResponse> call = apiService.RateofNovel(idnovel);
        call.enqueue(new Callback<RatingResponse>() {
            @Override
            public void onResponse(Call<RatingResponse> call, Response<RatingResponse> response) {
                if(response.isSuccessful()){
                    RatingResponse ratingResponse = response.body();
                    String x = String.valueOf(ratingResponse.getAVG());
                    TextView vote = findViewById(R.id.ratingtxt);
                    vote.setText(x+"/5");
                }else {
                    Toast.makeText(NovelInfor.this, "không lấy được dữ liệu", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RatingResponse> call, Throwable t) {
                Toast.makeText(NovelInfor.this,  t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void sendComment(int idnovel,int idacc,String Content){
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<CommentResponse> call = apiService.CommentRes(idnovel,idacc,Content);
        call.enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                if(response.isSuccessful()){
                    CommentResponse commentResponse = response.body();
                    if(commentResponse.isSuccess()) {
                        Toast.makeText(NovelInfor.this,commentResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(NovelInfor.this, commentResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(NovelInfor.this,"Can not connect to server",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                Toast.makeText(NovelInfor.this,  t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
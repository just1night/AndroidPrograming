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
import com.example.myapplication.api.ApiService;
import com.example.myapplication.api.LoginResponse;
import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.api.VoteResponse;
import com.example.myapplication.classobject.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
//                Intent intent = new Intent();
//                intent.setClass(NovelInfor.this, HomeAct.class);
//                startActivity(intent);
                finish();
            }
        });

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
        Button submit = findViewById(R.id.btnsubmitmess);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                        // Đăng nhập thành công, chuyển đến màn hình chính hoặc thực hiện các hành động khác
                        Toast.makeText(NovelInfor.this, voteResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        // Đăng nhập không thành công, thông báo lỗi
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



}
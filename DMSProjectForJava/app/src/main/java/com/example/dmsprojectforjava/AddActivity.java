package com.example.dmsprojectforjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {


    Button bt_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ServerApi serverApi;
        createPost("Test topic ", "Test subject");
        editPost("date", "topic", "subject");

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void createPost(String topic, String subject) {

        ServerApi ServerApi = ApiProvider.getInstance().create(ServerApi.class);
        ServerResponse post = new ServerResponse(topic, subject);
        Call<ServerResponse> call = ServerApi.getPost(post);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                System.out.println("ㅇㅇㄴㅇ");
            }
        });

    }
    public void editPost(String data, String topic, String subject) {
        ServerApi ServerApi = ApiProvider.getInstance().create(ServerApi.class);

        ServerResponse post = new ServerResponse(topic, subject);

        Call<ServerResponse> call = ServerApi.editPost(data, post);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }
}
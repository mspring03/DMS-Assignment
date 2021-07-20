package com.example.dmsprojectforjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MainData> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArraySet<String> SpinnerList;
    private ServerRequest requestValue;
    private ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        mainAdapter = new MainAdapter(arrayList, getApplicationContext());
        recyclerView.setAdapter(mainAdapter);

        button = (ImageButton) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

    }

    private void StartSetPost(ServerRequest serverRequest){
        int TotleElements = serverRequest.getPosts().size();
        Log.d("오류", "dsdadasdad");
        for(int i = 0; i < TotleElements; i++) {
            JsonObject jsonObject = serverRequest.getPosts().get(i);
            String startId = jsonObject.get("id").toString();
            String startTopic = jsonObject.get("title").toString();
            String startSubject = jsonObject.get("content").toString();
            String startDate = jsonObject.get("createDate").toString();

            MainData mainData = new MainData(startTopic, startSubject, startId, startDate);
            arrayList.add(mainData);

            mainAdapter.notifyDataSetChanged();
        }
    }
    public static void deletePost(String data) {
        ServerApi ServerApi = ApiProvider.getInstance().create(ServerApi.class);
        ServerApi.getDelete(data).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        arrayList.clear();
        mainAdapter.notifyDataSetChanged();

        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);
        Call<ServerRequest> call = serverApi.getServer();
        call.enqueue(new Callback<ServerRequest>() {
            @Override
            public void onResponse(Call<ServerRequest> call, Response<ServerRequest> response) {
                requestValue = response.body();
                StartSetPost(requestValue);
            }

            @Override
            public void onFailure(Call<ServerRequest> call, Throwable t) {
            }
        });


    }


}
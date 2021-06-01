package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginpage.models.Insignia;
import com.example.loginpage.models.Usuario;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsigniasActivity extends AppCompatActivity {
    Button volver;
    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static Minimo2API minimo2API;
    private List<Insignia> insigniaList = new ArrayList<>();
    private String userName;
    private Usuario user;
    ImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insignias);

        volver = findViewById(R.id.volverInsignias_btn);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (view.getContext(), DashboardActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(minimo2API.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        minimo2API = retrofit.create(Minimo2API.class);

        recyclerView = (RecyclerView)findViewById(R.id.recycle_insignias);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Call<List<Insignia>> followers = (Call<List<Insignia>>) minimo2API.getInsignias();

        followers.enqueue(new Callback<List<Insignia>>() {
            @Override
            public void onResponse(Call<List<Insignia>> call, Response<List<Insignia>> response) {
                insigniaList = response.body();
                mAdapter = new InsigniasAdapter(insigniaList);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Insignia>> call, Throwable t) {
                Log.i("ListFollowers", "Failure " + t.getMessage());
            }
        });


    }
}

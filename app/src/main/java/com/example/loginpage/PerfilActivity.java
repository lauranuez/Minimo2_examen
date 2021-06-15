package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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

public class PerfilActivity extends AppCompatActivity {
    Button volver;
    private String idUser;
    TextView nombre;
    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static Minimo2API minimo2API;
    private List<Insignia> insigniaList = new ArrayList<>();
    private Usuario user;

    private void recibirDatos(){
        Bundle extras = getIntent().getExtras();
        idUser = extras.getString("username");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        nombre = (TextView) findViewById(R.id.nameUser_txt);

        recibirDatos();

        volver = findViewById(R.id.volverPerfil_btn);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (view.getContext(), DashboardActivity.class);
                startActivityForResult(intent, 0);
            }
        });


        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Minimo2API.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        minimo2API = retrofit.create(Minimo2API.class);

        recyclerView = (RecyclerView)findViewById(R.id.recyclePerfil);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


       Call<Usuario> userCall = minimo2API.getInfoUser(idUser);

        userCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                user = response.body();
                nombre.setText(user.getName());
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.i("Usuario", "Failure " + t.getMessage());
            }
        });

        Call<List<Insignia>> insigniasCall = (Call<List<Insignia>>) minimo2API.getInsigniasByUser(idUser);
        insigniasCall.enqueue(new Callback<List<Insignia>>() {
            @Override
            public void onResponse(Call<List<Insignia>> call, Response<List<Insignia>> response) {
                insigniaList = response.body();
                mAdapter = new InsigniasAdapter(insigniaList,getApplicationContext());
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Insignia>> call, Throwable t) {
                Log.i("ListInsignia", "Failure " + t.getMessage());
            }
        });
    }
}

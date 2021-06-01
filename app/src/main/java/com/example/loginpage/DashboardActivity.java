package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {
    Button perfil;
    Button insignias;
    private String idUser;

    private void recibirDatos(){
        Bundle extras = getIntent().getExtras();
        idUser = extras.getString("username");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        perfil = findViewById(R.id.perfil_btn);
        insignias = findViewById(R.id.insignias_btn);

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (view.getContext(), PerfilActivity.class);
                intent.putExtra("username",idUser);
                startActivityForResult(intent, 0);
            }
        });

        insignias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (view.getContext(), InsigniasActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }
}

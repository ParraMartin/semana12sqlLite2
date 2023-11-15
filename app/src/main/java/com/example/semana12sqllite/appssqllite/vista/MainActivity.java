package com.example.semana12sqllite.appssqllite.vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.example.semana12sqllite.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtén referencias a los botones
        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        Button btnMantener = findViewById(R.id.btnMantener);
        Button btnListar = findViewById(R.id.btnListar);

        // Configura clic listeners para los botones
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,   register.class);
                startActivity(intent);
            }
        });

        btnMantener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, mainternance.class);
                startActivity(intent);
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Listar.class);
                startActivity(intent);
            }
        });
    }
}

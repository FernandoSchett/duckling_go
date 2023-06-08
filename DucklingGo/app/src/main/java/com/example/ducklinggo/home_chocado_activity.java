package com.example.ducklinggo;

import static com.example.ducklinggo.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home_chocado_activity extends AppCompatActivity {

    Button botao_duckdex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_home);

        botao_duckdex = findViewById(id.duckdex_button);

        botao_duckdex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.ducklinggo.duck_dex_activity");
                startActivity(intent);
            }
        });
    }
}
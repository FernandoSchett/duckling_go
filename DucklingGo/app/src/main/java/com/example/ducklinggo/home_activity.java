package com.example.ducklinggo;

import static com.example.ducklinggo.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home_activity extends AppCompatActivity {

    Button botao_chocar;
    Button botao_duckdex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_home);

        botao_chocar = findViewById(id.chocar_button);
        botao_duckdex = findViewById(id.duckdex_button);

        botao_duckdex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_activity.this, duck_dex_activity.class);
                startActivity(intent);
            }
        });
    }
}
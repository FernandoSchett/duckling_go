package com.example.ducklinggo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login_activity extends AppCompatActivity {

    EditText inserir_usuario;
    EditText inserir_senha;
    Button login_button;
    Button cadastro_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inserir_usuario = findViewById(R.id.login_usuario);
        inserir_senha = findViewById(R.id.login_senha);
        login_button = findViewById(R.id.botao_login);
        cadastro_button = findViewById(R.id.botao_cadastro);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_activity.this, home_activity.class);
                startActivity(intent);
            }
        });

        cadastro_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(login_activity.this, register_activity.class);
                startActivity(intent);
            }
        });

    }
}
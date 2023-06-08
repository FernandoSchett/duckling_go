package com.example.ducklinggo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class register_activity extends AppCompatActivity {

    EditText inserir_usuario;
    EditText inserir_email;
    EditText inserir_senha;
    Button cadastrar_button;
    Button voltar_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inserir_email = findViewById(R.id.cadastro_email);
        inserir_usuario = findViewById(R.id.cadastro_usuario);
        inserir_senha = findViewById(R.id.cadastro_senha);
        cadastrar_button = findViewById(R.id.botao_cadastro);
        voltar_button = findViewById(R.id.botao_voltar);

        cadastrar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // LOGICA DE INSERIR NO BANCO
            }
        });

        voltar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register_activity.this, login_activity.class);
                startActivity(intent);
            }
        });
    }
}
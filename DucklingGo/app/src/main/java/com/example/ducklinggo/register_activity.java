package com.example.ducklinggo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class register_activity extends AppCompatActivity {

    EditText inserir_usuario_edit_text;
    EditText inserir_email_edit_text;
    EditText inserir_senha_edit_text;
    Button register_button;
    Button voltar_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inserir_email_edit_text = findViewById(R.id.email_edit_text);
        inserir_usuario_edit_text = findViewById(R.id.cadastro_usuario_edit_text);
        inserir_senha_edit_text = findViewById(R.id.cadastro_senha_edit_text);
        register_button = findViewById(R.id.register_button);
        voltar_button = findViewById(R.id.cadastro_botao_voltar);

        register_button.setOnClickListener(new View.OnClickListener() {
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
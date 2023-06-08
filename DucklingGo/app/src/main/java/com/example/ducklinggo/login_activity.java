package com.example.ducklinggo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login_activity extends AppCompatActivity {

    EditText inserir_usuario_edit_text;
    EditText inserir_senha_edit_text;
    Button login_button;
    Button register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inserir_usuario_edit_text = findViewById(R.id.usuario_edit_text);
        inserir_senha_edit_text = findViewById(R.id.senha_edit_text);
        login_button = findViewById(R.id.login_button);
        register_button = findViewById(R.id.register_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_activity.this, home_activity.class);
                startActivity(intent);
            }
        });

        register_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(login_activity.this, register_activity.class);
                startActivity(intent);
            }
        });

    }
}
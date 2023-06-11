package com.example.ducklinggo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.database.*;
import com.example.sessions.UserSession;

public class login_activity extends AppCompatActivity {

    // Elementos do design
    private EditText inserir_usuario_edit_text;
    private EditText inserir_senha_edit_text;
    private Button login_button;
    private Button login_register_button;

    // Banco de dados
    private userDAO userDAO;
    private pokemonDAO pokemonDAO;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inserir_usuario_edit_text = findViewById(R.id.usuario_edit_text);
        inserir_senha_edit_text = findViewById(R.id.senha_edit_text);
        login_button = findViewById(R.id.login_button);
        login_register_button = findViewById(R.id.login_register_button);

        // Criando Instancia do banco
        dbHelper = new DatabaseHelper(getApplicationContext());

        // Criando instâncias dos DAOs para as classes modelo
        userDAO = new userDAO(this);
        pokemonDAO = new pokemonDAO(this);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSession userSession;
                AlertDialog.Builder builder = new AlertDialog.Builder(login_activity.this);
                String username = inserir_usuario_edit_text.getText().toString();
                String password = inserir_senha_edit_text.getText().toString();

                if(userDAO.loginExists(username, password)){
                    Intent intent = new Intent(login_activity.this, home_activity.class);
                    startActivity(intent);

                    userSession = UserSession.getInstance();

                    userSession.setUserId(userDAO.getUserIdByUsername(username));
                    userSession.setUsername(username);

                }else{

                    builder.setTitle("Login inválido!");
                    builder.setMessage("Usuario ou senha incorretos!");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }
        });

        login_register_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(login_activity.this, register_activity.class);
                startActivity(intent);
            }
        });

    }
}
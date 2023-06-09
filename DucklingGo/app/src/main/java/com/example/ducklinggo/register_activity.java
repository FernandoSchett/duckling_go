package com.example.ducklinggo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.database.DatabaseHelper;
import com.example.database.pokemonDAO;
import com.example.database.userDAO;
import com.example.models.User;

public class register_activity extends AppCompatActivity {

    // Elementos do design
    EditText inserir_usuario_edit_text;
    EditText inserir_email_edit_text;
    EditText inserir_senha_edit_text;
    Button register_button;
    Button voltar_button;

    // Banco de dados
    private userDAO userDAO;
    private pokemonDAO pokemonDAO;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inserir_email_edit_text = findViewById(R.id.email_edit_text);
        inserir_usuario_edit_text = findViewById(R.id.cadastro_usuario_edit_text);
        inserir_senha_edit_text = findViewById(R.id.cadastro_senha_edit_text);
        register_button = findViewById(R.id.register_button);
        voltar_button = findViewById(R.id.cadastro_botao_voltar);

        // Criando Instancia do banco
        dbHelper = new DatabaseHelper(getApplicationContext());


        // Criando instâncias dos DAOs para as classes modelo
        userDAO = new userDAO(this);
        pokemonDAO = new pokemonDAO(this);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(register_activity.this);

                User user = new User();
                user.setUsername(inserir_usuario_edit_text.getText().toString());
                user.setEmail(inserir_email_edit_text.getText().toString());
                user.setPassword(inserir_senha_edit_text.getText().toString());

                if(!userDAO.isUsernameExists(user.getUsername()) && !userDAO.isEmailExists(user.getEmail())){
                    userDAO.Inserir(user);
                    builder.setTitle("Usuário inserido com sucesso!");
                    builder.setMessage("Nome do usuário inserido: " + user.getUsername());
                }else{
                    builder.setTitle("Nome de usuário ou email já existente, por favor escolha outro!");
                }

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
package com.example.ducklinggo;

import static com.example.ducklinggo.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.database.DatabaseHelper;
import com.example.database.pokemonDAO;
import com.example.database.userDAO;
import com.example.models.Pokemon;
import com.example.services.PokemonService;
import com.example.sessions.PokemonSession;
import com.example.sessions.UserSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class home_activity extends AppCompatActivity {

    // Elementos do design
    Button chocar_button;
    Button duckdex_button;
    TextView username;

    // Lidando com as datas
    TextView date;
    Date currentDate;
    SimpleDateFormat dateFormat;
    String formattedDate;

    // Consumindo a API com o retrofit
    private Retrofit retrofit;
    PokemonService pokemonService;

    // Banco de dados
    private userDAO userDAO;
    private pokemonDAO pokemonDAO;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_home);

        chocar_button = findViewById(id.chocar_button);
        duckdex_button = findViewById(id.duckdex_button);
        username = findViewById(id.username_textview);
        date = findViewById(id.date_textview);

        // Criando Instancia do banco
        dbHelper = new DatabaseHelper(getApplicationContext());


        // Criando instâncias dos DAOs para as classes modelo
        userDAO = new userDAO(this);
        pokemonDAO = new pokemonDAO(this);

        username.setText(UserSession.getInstance().getUsername());

        currentDate = new Date();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        formattedDate = dateFormat.format(currentDate);

        date.setText(formattedDate);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pokemonService = retrofit.create(PokemonService.class);

        chocar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Random random = new Random();
               int pokemonId = random.nextInt(248);

                Call<Pokemon> call = pokemonService.getPokemon(pokemonId);

                call.enqueue(new Callback<Pokemon>() {
                    @Override
                    public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                        if (response.isSuccessful()) {

                           Pokemon pokemon = response.body();
                           PokemonSession pokemonSession = PokemonSession.getInstance();
                           pokemonSession.setPokemonData(pokemon.getSprites().getFrontDefault(), pokemon.getName_pokemon(), pokemon.getId_api_pokemon());

                           pokemon.setId_user(UserSession.getInstance().getUserId());
                           pokemonDAO.Inserir(pokemon);

                        }
                    }

                    @Override
                    public void onFailure(Call<Pokemon> call, Throwable t) {
                        // Ocorreu um erro durante a solicitação
                        // Lida com o erro
                    }
                });

                Intent intent = new Intent(home_activity.this, home_chocado_activity.class);
                intent.putExtra("imageURL", PokemonSession.getInstance().getUrl());
                startActivity(intent);
            }
        });

        duckdex_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_activity.this, duck_dex_activity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_button) {
            Intent intent = new Intent(home_activity.this, login_activity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
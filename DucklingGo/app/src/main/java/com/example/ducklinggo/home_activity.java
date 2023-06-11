package com.example.ducklinggo;

import static com.example.ducklinggo.R.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.service.autofill.UserData;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.database.DatabaseHelper;
import com.example.database.pokemonDAO;
import com.example.database.userDAO;
import com.example.models.Pokemon;
import com.example.services.PokemonService;
import com.example.sessions.PokemonSession;
import com.example.sessions.UserSession;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
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
    ImageView pokemon_image_image_view;

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
        pokemon_image_image_view = findViewById(id.broken_egg_imageview);

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

                String last_hatch_string = userDAO.getDateByID(UserSession.getInstance().getUserId());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(last_hatch_string, formatter);

                LocalDateTime currentDate = LocalDateTime.now();

                Duration duration = Duration.between(dateTime, currentDate);

                if(duration.toHours() >= 24) {
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
                        public void onFailure(Call<Pokemon> call, Throwable t) { }
                    });

                    userDAO.updateLastHatch(UserSession.getInstance().getUserId(), formattedDate);

                    Picasso.get()
                            .load(PokemonSession.getInstance().getUrl())
                            .into(pokemon_image_image_view);

                }else{

                    Duration one_day_duration = Duration.ofDays(1);

                    long time_remaning = one_day_duration.toMillis() - duration.toMillis();

                    long hours = time_remaning / 3600000;
                    time_remaning -= hours * 3600000;

                    long minutes = time_remaning / 60000;
                    time_remaning -= minutes * 60000;

                    long totalSeconds = time_remaning / 1000;

                    AlertDialog.Builder builder = new AlertDialog.Builder(home_activity.this);
                    builder.setTitle("Ainda não pode chocar :(");
                    builder.setMessage("" +
                            "Você chocou um pokemon recentemente, espere mais " + hours + " H " + minutes + " M " + totalSeconds + " SEG ");

                    builder.setPositiveButton("Oskey", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) { dialog.dismiss();}
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }


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
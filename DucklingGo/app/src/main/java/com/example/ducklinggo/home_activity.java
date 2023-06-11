package com.example.ducklinggo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import java.time.Duration;
import java.time.LocalDateTime;
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
    java.text.SimpleDateFormat dateFormat;
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

        setContentView(R.layout.activity_home);

        link_all_elements();

        set_database();

        set_username();
        set_date();

        link_with_retrofit();
        hatch_button();
        duckdex_button();

    }

    public void link_all_elements(){
        chocar_button = findViewById(R.id.chocar_button);
        duckdex_button = findViewById(R.id.duckdex_button);
        username = findViewById(R.id.username_textview);
        date = findViewById(R.id.date_textview);
        pokemon_image_image_view = findViewById(R.id.broken_egg_imageview);
    }

    public void set_database(){
        dbHelper = new DatabaseHelper(getApplicationContext());
        userDAO = new userDAO(this);
        pokemonDAO = new pokemonDAO(this);
    }

    public void set_username(){
        username.setText(UserSession.getInstance().getUsername());
    }

    public void set_date(){
        currentDate = new Date();
        dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        formattedDate = dateFormat.format(currentDate);

        date.setText(formattedDate);
    }

    public void link_with_retrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pokemonService = retrofit.create(PokemonService.class);
    }

    public void duckdex_button(){
        duckdex_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_activity.this, duck_dex_activity.class);
                startActivity(intent);
            }
        });
    }
    public void hatch_button(){
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

                    userDAO.updateLastHatch(UserSession.getInstance().getUserId(), currentDate);

                    Picasso.get()
                            .load(PokemonSession.getInstance().getUrl())
                            .into(pokemon_image_image_view);

                } else {
                    Duration one_day_duration = Duration.ofDays(1);

                    long time_remaining = one_day_duration.toMillis() - duration.toMillis();

                    long hours = time_remaining / (1000 * 60 * 60);
                    time_remaining -= hours * (1000 * 60 * 60);

                    long minutes = time_remaining / (1000 * 60);
                    time_remaining -= minutes * (1000 * 60);

                    long totalSeconds = time_remaining / 1000;

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

package com.example.ducklinggo;

import com.example.ducklinggo.R.*;
import com.example.sessions.PokemonSession;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sessions.UserSession;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class home_chocado_activity extends AppCompatActivity {

    Button duckdex_button;
    TextView left_time;
    ImageView hatch_pokemon_img;
    TextView pokemon_name;
    TextView username;
    TextView date;

    // Lidando com as datas
    Date currentDate;
    SimpleDateFormat dateFormat;
    String formattedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_home_chocado);

        duckdex_button = findViewById(id.duckdex_button);
        left_time = findViewById(id.left_time_textview);
        hatch_pokemon_img = findViewById(id.hatch_pokemon_img);
        pokemon_name = findViewById(id.pokemon_name);
        username = findViewById(id.username_textview);
        date = findViewById(id.date_textview);

        username.setText(UserSession.getInstance().getUsername());

        currentDate = new Date();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        formattedDate = dateFormat.format(currentDate);

        date.setText(formattedDate);

        Picasso.get()
                .load(PokemonSession.getInstance().getUrl())
                .resize(800, 600)
                .into(hatch_pokemon_img);

        pokemon_name.setText(PokemonSession.getInstance().getName());

        duckdex_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_chocado_activity.this, duck_dex_activity.class);
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
            Intent intent = new Intent(home_chocado_activity.this, login_activity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
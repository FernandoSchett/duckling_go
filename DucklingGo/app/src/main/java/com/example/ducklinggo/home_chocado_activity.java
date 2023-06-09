package com.example.ducklinggo;

import com.example.ducklinggo.R.*;
import com.example.services.PokemonService;
import com.example.sessions.PokemonSession;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class home_chocado_activity extends AppCompatActivity {

    Button duckdex_button;
    TextView lefTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_home_chocado);

        duckdex_button = findViewById(id.duckdex_button);
        lefTime = findViewById(id.left_time_textview);

        lefTime.setText(PokemonSession.getInstance().getName());

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
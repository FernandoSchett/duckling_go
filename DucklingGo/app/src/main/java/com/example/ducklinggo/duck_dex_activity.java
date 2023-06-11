package com.example.ducklinggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adapter.ItemAdapter;
import com.example.database.DatabaseHelper;
import com.example.database.pokemonDAO;
import com.example.database.userDAO;
import com.example.models.ImageItem;
import com.example.models.Pokemon;
import com.example.sessions.UserSession;

import java.util.ArrayList;
import java.util.List;

public class duck_dex_activity extends AppCompatActivity {
    Button voltar_button;
    ListView pokemons;

    // Banco de dados
    private userDAO userDAO;
    private pokemonDAO pokemonDAO;
    private DatabaseHelper dbHelper;

    List<Pokemon> pokemons_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duck_dex);

        voltar_button = findViewById(R.id.voltar_button);
        pokemons = findViewById(R.id.recyclerview_pokemon);

        // Criando Instancia do banco
        dbHelper = new DatabaseHelper(getApplicationContext());

        // Criando instâncias dos DAOs para as classes modelo
        userDAO = new userDAO(this);
        pokemonDAO = new pokemonDAO(this);

        pokemons_list = pokemonDAO.getPokemonsFromDatabase(UserSession.getInstance().getUserId());

        ArrayList<ImageItem> arrayImageItem = new ArrayList<>();

        for(Pokemon p : pokemons_list){
            ImageItem imageItem = new ImageItem(p.getSprites().getFrontDefault(), p.getName_pokemon());
            arrayImageItem.add(imageItem);
        }

        ItemAdapter itemAdapter = new ItemAdapter(this, arrayImageItem);

        pokemons.setAdapter(itemAdapter);

        voltar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(duck_dex_activity.this, home_activity.class);
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
            Intent intent = new Intent(duck_dex_activity.this, login_activity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<ImageItem> createImageItemList() {
        List<ImageItem> imageItemList = new ArrayList<>();

        // Itera pelos URLs e descrições para criar os ImageItems
        for(Pokemon p : pokemons_list) {

            ImageItem imageItem = new ImageItem(p.getSprites().getFrontDefault(), p.getName_pokemon());
            imageItemList.add(imageItem);
        }

        return imageItemList;
    }

}
package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.models.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class pokemonDAO {

    private DatabaseHelper conexao;
    private SQLiteDatabase banco;
    private String table_name = "TB_pokemon";

    public pokemonDAO(Context  context){
        conexao = new DatabaseHelper(context);
        banco = conexao.getWritableDatabase();
    }

    public long Inserir(Pokemon pokemon){
        ContentValues values = new ContentValues();
        values.put("ID_api_pokemon", pokemon.getId_api_pokemon());
        values.put("ID_user", pokemon.getId_user());
        values.put("name_pokemon", pokemon.getName_pokemon());
        values.put("URL_img_pokemon", pokemon.getSprites().getFrontDefault());

        return banco.insert(table_name, null, values );
    }

    public ArrayList<Pokemon> getPokemonsFromDatabase(int id) {
        ArrayList<Pokemon> pokemons = new ArrayList<>();

        banco = conexao.getReadableDatabase();

        String[] columns = {"ID_api_pokemon", "ID_user", "name_pokemon", "URL_img_pokemon"};  // Colunas desejadas do banco de dados
        String selection = "ID_user = ?";  // Condição para selecionar a linha com o ID desejado
        String[] selectionArgs = {String.valueOf(id)};  // Argumento para substituir o placeholder da condição

        Cursor cursor = banco.query(table_name, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Pokemon pokemon = new Pokemon();
                int column_index = cursor.getColumnIndex("name_pokemon");
                pokemon.setName_pokemon(cursor.getString(column_index));

                column_index = cursor.getColumnIndex("URL_img_pokemon");
                pokemon.getSprites().setFrontDefault(cursor.getString(column_index));

                pokemons.add(pokemon);
            } while (cursor.moveToNext());
        }

        cursor.close();
        banco.close();

        return pokemons;
    }



    public void Excluir(Pokemon pokemon){

        banco.delete(table_name, "id=?", new String[]{String.valueOf(pokemon.getId_pokemon())}  );
    }
}
package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.models.Pokemon;

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

//    public List<Cliente> Listar(){
//        List<Cliente> agenda =  new ArrayList<>();
//        //select * from agenda
//        Cursor cursor = banco.query("agenda", new String[]{"id", "nome", "email","idade"},
//                null, null, null, null, null);
//
//        while(cursor.moveToNext()){
//            Cliente a = new Cliente();
//            a.setId(cursor.getInt(0));
//            a.setNome(cursor.getString(1));
//            a.setEmail(cursor.getString(2));
//            a.setIdade(cursor.getInt(3));
//            agenda.add(a);
//        }
//        return agenda;
//    }

    public void Excluir(Pokemon pokemon){

        banco.delete(table_name, "id=?", new String[]{String.valueOf(pokemon.getId_pokemon())}  );
    }
}
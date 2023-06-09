package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.models.User;

public class userDAO {

    private DatabaseHelper conexao;
    private SQLiteDatabase banco;

    public userDAO(Context  context){
        conexao = new DatabaseHelper(context);
        banco = conexao.getWritableDatabase();
    }

    public long Inserir(User user){
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        values.put("DT_last_hatch", user.getDT_last_hatch());

        return banco.insert("TB_user", null, values );
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

    public void Excluir(User user){

        banco.delete("TB_user", "id=?", new String[]{String.valueOf(user.getId_user())}  );
    }
}
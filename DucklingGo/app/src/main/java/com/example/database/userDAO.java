package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.models.User;

import java.util.Date;

public class userDAO {

    private DatabaseHelper conexao;
    private SQLiteDatabase banco;
    private String table_name = "TB_user";

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

        return banco.insert(table_name, null, values );
    }

    public void updateLastHatch(int id_user, String date){
        SQLiteDatabase db = conexao.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put("DT_last_hatch", date);

        String selection = "ID_user = ?";
        String[] selectionArgs = { String.valueOf(id_user) };

        db.update(table_name, values, selection, selectionArgs);
    }

    public String getDateByID(int id) {
        banco  = conexao.getReadableDatabase();

        String[] columns = {"DT_last_hatch"};  // Coluna que contém a data
        String selection = "ID_user = ?";  // Condição para selecionar o registro com o ID desejado
        String[] selectionArgs = {String.valueOf(id)};  // Argumento para substituir o placeholder da condição

        Cursor cursor = banco.query(table_name, columns, selection, selectionArgs, null, null, null);

        String date = "";

        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("DT_last_hatch");
            date = cursor.getString(columnIndex);
        }

        cursor.close();
        banco.close();

        if(date == null){
            return "08/03/2003";
        }else {
            return date;
        }

    }

    public int getUserIdByUsername(String username) {
        SQLiteDatabase db = conexao.getReadableDatabase();
        String[] columns = { "ID_user" };
        String selection = "username = ?";
        String[] selectionArgs = { username };

        Cursor cursor = db.query(table_name, columns, selection, selectionArgs, null, null, null);

        int userId = 0;

        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("ID_user");
            userId = cursor.getInt(columnIndex);
        }

        cursor.close();
        return userId;
    }

    public boolean loginExists(String username, String password) {
        SQLiteDatabase db = conexao.getReadableDatabase();
        String[] columns = { "ID_user" };
        String selection = "username = ? AND password = ?";
        String[] selectionArgs = { username, password };

        Cursor cursor = db.query(table_name, columns, selection, selectionArgs, null, null, null);
        boolean loginSuccessful = cursor.getCount() > 0;

        cursor.close();
        return loginSuccessful;
    }

    public boolean isUsernameExists(String username) {
        SQLiteDatabase db = conexao.getReadableDatabase();
        String[] columns = {"ID_user"};
        String selection = "username = ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(table_name, columns, selection, selectionArgs, null, null, null);
        boolean usernameExists = cursor.getCount() > 0;

        cursor.close();
        return usernameExists;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = conexao.getReadableDatabase();
        String[] columns = {"ID_user"};
        String selection = "email = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(table_name, columns, selection, selectionArgs, null, null, null);
        boolean usernameExists = cursor.getCount() > 0;

        cursor.close();
        return usernameExists;
    }

}
package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Definição da tabela de usuário
    private static final String TB_user = "CREATE TABLE IF NOT EXISTS TB_user(" +
            "ID_user INTEGER primary key autoincrement, " +
            "username VARCHAR(200) , " +
            "email VARCHAR(100), " +
            "password VARCHAR(300), " +
            "DT_last_hatch String)";

    // Definição da tabela de pokemon
    private static final String TB_pokemon = "CREATE TABLE IF NOT EXISTS TB_pokemon (" +
            "ID_pokemon INTEGER PRIMARY KEY autoincrement, " +
            "ID_api_pokemon INTEGER, " +
            "ID_user INTEGER, " +
            "name_pokemon VARCHAR(100), " +
            "URL_img_pokemon VARCHAR(500), " +
            "FOREIGN KEY(ID_user) REFERENCES TB_user(ID_user))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação das tabelas
        db.execSQL(TB_user);
        db.execSQL(TB_pokemon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualização do esquema do banco de dados (se necessário)

        db.execSQL("DROP TABLE IF EXISTS TB_user");
        db.execSQL("DROP TABLE IF EXISTS TB_pokemon");
        onCreate(db);
    }
}
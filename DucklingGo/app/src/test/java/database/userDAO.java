package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import models.User;

public class UserDAO {

    private DatabaseHelper conexao;
    private SQLiteDatabase banco;

    public UserDAO(Context  context){
        conexao = new DatabaseHelper(context);
        banco = conexao.getWritableDatabase();
    }

    public long Inserir(User user){
        ContentValues values = new ContentValues();
        values.put("nome", user.getNome());
        values.put("email", user.getEmail());
        values.put("idade", user.getIdade());

        return banco.insert("agenda", null, values );
    }

    public List<Cliente> Listar(){
        List<Cliente> agenda =  new ArrayList<>();
        //select * from agenda
        Cursor cursor = banco.query("agenda", new String[]{"id", "nome", "email","idade"},
                null, null, null, null, null);

        while(cursor.moveToNext()){
            Cliente a = new Cliente();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setEmail(cursor.getString(2));
            a.setIdade(cursor.getInt(3));
            agenda.add(a);
        }
        return agenda;
    }

    public void Excluir(Cliente a){

        banco.delete("agenda", "id=?", new String[]{a.getId().toString()}  );
    }
}
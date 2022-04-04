package com.example.crudgames;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class Conexao extends SQLiteOpenHelper {
    private static final String name = "banco.db";
    private static final int version = 3;

    public Conexao(Context context) {
        super(context, "banco.db", null, version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table meusjogos (id integer primary key autoincrement, nome varchar(50), nick varchar(50), jogo varchar(50), melhorRank varchar(50))");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists meusjogos");
        this.onCreate(db);
    }
}
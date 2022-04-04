package com.example.crudgames;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class JogoDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public JogoDAO(Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Jogo jogo) {
        ContentValues values = new ContentValues();
        values.put("nome", jogo.getNome());
        values.put("nick", jogo.getNick());
        values.put("jogo", jogo.getJogo());
        values.put("melhorRank", jogo.getMelhorRank());
        return banco.insert("meusjogos",null, values);
    }

    public List<Jogo> obterTodos() {
        List<Jogo> jogadores = new ArrayList<>();
        Cursor cursor = banco.query("meusjogos", new String[]{"id", "nome", "nick", "jogo", "melhorRank"}, null, null, null, null, "nome");

        while(cursor.moveToNext()) {
            Jogo j = new Jogo();
            j.setId(cursor.getInt(0));
            j.setNome(cursor.getString(1));
            j.setNick(cursor.getString(2));
            j.setJogo(cursor.getString(3));
            j.setMelhorRank(cursor.getString(4));
            jogadores.add(j);
        }

        return jogadores;
    }

    public void excluir(Jogo j) {
        banco.delete("meusjogos", "id = ?", new String[]{j.getId().toString()});
    }

    public void atualizar(Jogo jogo) {
        ContentValues values = new ContentValues();
        values.put("nome", jogo.getNome());
        values.put("nick", jogo.getNick());
        values.put("jogo", jogo.getJogo());
        values.put("melhorRank", jogo.getMelhorRank());
        banco.update("meusjogos", values, "id = ?", new String[]{jogo.getId().toString()});
    }
}
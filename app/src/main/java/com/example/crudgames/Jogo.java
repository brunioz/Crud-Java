package com.example.crudgames;

import java.io.Serializable;

public class Jogo implements Serializable {
    private Integer id;
    private String nome;
    private String nick;
    private String jogo;
    private String melhorRank;

    public Jogo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getJogo() {
        return jogo;
    }

    public void setJogo(String jogo) {
        this.jogo = jogo;
    }

    public String getMelhorRank() {
        return melhorRank;
    }

    public void setMelhorRank(String melhorRank) {
        this.melhorRank = melhorRank;
    }

    public String toString() {
        return nome;
    }
}
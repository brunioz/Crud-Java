package com.example.crudgames;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

  public class CadastroJogadoresActivity extends AppCompatActivity {
    private EditText nome;
    private EditText nick;
    private EditText jogo;
    private EditText melhorRank;
    private JogoDAO dao;
    private Jogo jogos = null;

    public CadastroJogadoresActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nome = findViewById(R.id.editNome);
        nick = findViewById(R.id.editNick);
        jogo = findViewById(R.id.editJogo);
        melhorRank = findViewById(R.id.editMelhorRank);
        dao = new JogoDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("jogos")) {
            jogos = (Jogo)it.getSerializableExtra("jogos");
            nome.setText(jogos.getNome());
            nick.setText(jogos.getNick());
            jogo.setText(jogos.getJogo());
            melhorRank.setText(jogos.getMelhorRank());
        }

    }

    public void salvar(View view) {
        if (nome.getText().toString().isEmpty() || nick.getText().toString().isEmpty() || jogo.getText().toString().isEmpty() || melhorRank.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor preencha todos os campos ", Toast.LENGTH_SHORT).show();
        } else {
            if (jogos == null) {
                jogos = new Jogo();
                jogos.setNome(nome.getText().toString());
                jogos.setNick(nick.getText().toString());
                jogos.setJogo(jogo.getText().toString());
                jogos.setMelhorRank(melhorRank.getText().toString());
                long id = dao.inserir(jogos);
                Toast.makeText(this, "Jogador inserido ", Toast.LENGTH_SHORT).show();
                voltar();
            } else {
                jogos.setNome(nome.getText().toString());
                jogos.setNick(nick.getText().toString());
                jogos.setJogo(jogo.getText().toString());
                jogos.setMelhorRank(melhorRank.getText().toString());
                dao.atualizar(jogos);
                Toast.makeText(this, "Jogador atualizado ", Toast.LENGTH_SHORT).show();
                voltar();
            }

        }

    }

    public void voltar() {
        Intent it = new Intent(this, ListarJogadoresActivity.class);
        startActivity(it);
    }
}